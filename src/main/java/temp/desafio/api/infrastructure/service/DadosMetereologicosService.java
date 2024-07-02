package temp.desafio.api.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.core.enums.TipoClima;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.core.exceptions.ValidacaoException;
import temp.desafio.api.core.usecase.CreateDadosMetereologicosUseCaseImp;
import temp.desafio.api.core.usecase.DeleteDadosMetereologicosUseCaseImp;
import temp.desafio.api.core.usecase.UpdateDadosMetereologicosUseCaseImp;
import temp.desafio.api.infrastructure.mappers.DadosMetereologicosEntityMapper;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicosEntity;
import temp.desafio.api.infrastructure.persistence.repositories.DadosMetereologicosRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DadosMetereologicosService implements IDadosMetereologicos {

    @Autowired
    private DadosMetereologicosRepository dadosMetereologicosRepository;

    @Autowired
    private DadosMetereologicosEntityMapper entityMapper;

    @Autowired
    private CreateDadosMetereologicosUseCaseImp createDadosMetereologicosUseCase;

    @Autowired
    private UpdateDadosMetereologicosUseCaseImp updateDadosMetereologicosUseCaseImp;

    @Autowired
    private DeleteDadosMetereologicosUseCaseImp deleteDadosMetereologicosUseCase;

    @Override
    public DadosMetereologicos createDadosMetereologicos(DadosMetereologicos dadosMetereologicos){
        DadosMetereologicosEntity dadosMetereologicosEntity = entityMapper.toDadosMetereologicosEntity(dadosMetereologicos);
        createDadosMetereologicosUseCase.execute(dadosMetereologicos);
        DadosMetereologicosEntity dadosMetereologicosEntitySaveEntity = dadosMetereologicosRepository.save(dadosMetereologicosEntity);
        return entityMapper.toDadosMetereologicos(dadosMetereologicosEntitySaveEntity);
    }

    @Override
    public DadosMetereologicos findByCidade(String cidade) {
        Optional<DadosMetereologicosEntity> dadosMetereologicosEntity = dadosMetereologicosRepository.findByCidade(cidade);
        return dadosMetereologicosEntity.map(entityMapper::toDadosMetereologicos).orElse(null);
    }

    @Override
    public List<DadosMetereologicos> getAllDadosMetereologicos() {
        return dadosMetereologicosRepository
                .findAll()
                .stream()
                .map(entityMapper::toDadosMetereologicos)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDadosMeterologicos(String cidade, LocalDate data, TipoTurno turno) {
        deleteDadosMetereologicosUseCase.execute(cidade, data, turno);
        Optional<DadosMetereologicosEntity> optionalDadosMetereologicosEntity =
                dadosMetereologicosRepository.findByCidadeAndDataAndTurno(cidade, data, turno);
        if (optionalDadosMetereologicosEntity.isPresent()){
            DadosMetereologicosEntity dadosMetereologicosEntity = optionalDadosMetereologicosEntity.get();
            dadosMetereologicosRepository.delete(dadosMetereologicosEntity);
        } else {
            throw new ValidacaoException("Evento climático não encontrado");
        }
    }

    @Override
    public DadosMetereologicos updateDadosMeterologicos(
            String cidade,
            LocalDate data,
            TipoTurno turno,
            DadosMetereologicos dadosMetereologicos) {

            Optional<DadosMetereologicosEntity> dadosMetereologicosEntity = dadosMetereologicosRepository.findByCidadeAndDataAndTurno(cidade,data,turno);

            updateDadosMetereologicosUseCaseImp.execute(dadosMetereologicos);

            if (dadosMetereologicosEntity.isPresent()) {
                DadosMetereologicosEntity existingDadosMetereologicosEntity = dadosMetereologicosEntity.get();

                String cidadeUpdate = dadosMetereologicos.cidade() == null ? existingDadosMetereologicosEntity.getCidade() : dadosMetereologicos.cidade();
                LocalDate dataUpdate = dadosMetereologicos.data() == null ? existingDadosMetereologicosEntity.getData() : dadosMetereologicos.data();
                TipoTurno turnoUpdate = dadosMetereologicos.turno() == null ? existingDadosMetereologicosEntity.getTurno() : dadosMetereologicos.turno();
                String tempMinUpdate = dadosMetereologicos.tempMin() == null ? existingDadosMetereologicosEntity.getTempMin() : dadosMetereologicos.tempMin();
                String tempMaxUpdate = dadosMetereologicos.tempMax() == null ? existingDadosMetereologicosEntity.getTempMax() : dadosMetereologicos.tempMax();
                TipoClima climaUpdate = dadosMetereologicos.clima() == null ? existingDadosMetereologicosEntity.getClima() : dadosMetereologicos.clima();
                String precipitacaoUpdate = dadosMetereologicos.precipitacao() == null ? existingDadosMetereologicosEntity.getPrecipitacao() : dadosMetereologicos.precipitacao();
                String umidadeUpdate = dadosMetereologicos.umidade() == null ? existingDadosMetereologicosEntity.getUmidade() : dadosMetereologicos.umidade();
                String velDoVentoUpdate = dadosMetereologicos.velDoVento() == null ? existingDadosMetereologicosEntity.getVelDoVento() : dadosMetereologicos.velDoVento();

                boolean localizador = dadosMetereologicosRepository.findClimaAtivoByCidadeAndDataAndTuno(cidadeUpdate, dataUpdate, turnoUpdate);
                if (localizador) {
                    if (
                            !cidadeUpdate.equals(cidade) ||
                            !dataUpdate.equals(data) ||
                            !turnoUpdate.equals(turno)
                    ) {
                        throw new ValidacaoException("Já existe um evento cadastrado nessa data");
                    }
                }

                existingDadosMetereologicosEntity.setCidade(cidadeUpdate);
                existingDadosMetereologicosEntity.setData(dataUpdate);
                existingDadosMetereologicosEntity.setTurno(turnoUpdate);
                existingDadosMetereologicosEntity.setTempMin(tempMinUpdate);
                existingDadosMetereologicosEntity.setTempMax(tempMaxUpdate);
                existingDadosMetereologicosEntity.setClima(climaUpdate);
                existingDadosMetereologicosEntity.setPrecipitacao(precipitacaoUpdate);
                existingDadosMetereologicosEntity.setUmidade(umidadeUpdate);
                existingDadosMetereologicosEntity.setVelDoVento(velDoVentoUpdate);

                DadosMetereologicosEntity dadosMetereologicosEntitySaved = dadosMetereologicosRepository.save(existingDadosMetereologicosEntity);
                return entityMapper.toDadosMetereologicos(dadosMetereologicosEntitySaved);
            } else {
                throw new ValidacaoException("Evento climático não localizado.");
            }
    }
}
