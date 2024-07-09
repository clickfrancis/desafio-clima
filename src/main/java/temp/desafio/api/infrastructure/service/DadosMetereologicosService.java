package temp.desafio.api.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.core.enums.TipoClima;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.core.exceptions.ValidacaoException;
import temp.desafio.api.core.usecase.CreateDadosMetereologicosUseCaseImp;
import temp.desafio.api.core.usecase.DeleteDadosMetereologicosUseCaseImp;
import temp.desafio.api.core.usecase.UpdateDadosMetereologicosUseCaseImp;
import temp.desafio.api.infrastructure.mappers.DadosMetereologicosEntityMapper;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicos;
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
    public DadosMetereologicosDTO createDadosMetereologicos(DadosMetereologicosDTO dadosMetereologicosDTO){
        DadosMetereologicos dadosMetereologicos = entityMapper.toDadosMetereologicos(dadosMetereologicosDTO);
        createDadosMetereologicosUseCase.execute(dadosMetereologicosDTO);
        DadosMetereologicos dadosMetereologicosEntitySave = dadosMetereologicosRepository.save(dadosMetereologicos);
        return entityMapper.toDadosMetereologicosDTO(dadosMetereologicosEntitySave);
    }

    @Override
    public DadosMetereologicosDTO findByCidade(String cidade) {
        Optional<DadosMetereologicos> dadosMetereologicosEntity = dadosMetereologicosRepository.findByCidade(cidade);
        return dadosMetereologicosEntity.map(entityMapper::toDadosMetereologicosDTO).orElse(null);
    }

    @Override
    public List<DadosMetereologicosDTO> getAllDadosMetereologicos() {
        return dadosMetereologicosRepository
                .findAll()
                .stream()
                .map(entityMapper::toDadosMetereologicosDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteDadosMeterologicos(String cidade, LocalDate data, TipoTurno turno) {
        deleteDadosMetereologicosUseCase.execute(cidade, data, turno);
        Optional<DadosMetereologicos> optionalDadosMetereologicosEntity =
                dadosMetereologicosRepository.findByCidadeAndDataAndTurno(cidade, data, turno);
        if (optionalDadosMetereologicosEntity.isPresent()){
            DadosMetereologicos dadosMetereologicos = optionalDadosMetereologicosEntity.get();
            dadosMetereologicosRepository.delete(dadosMetereologicos);
        } else {
            throw new ValidacaoException("Evento climático não encontrado");
        }
    }

    @Override
    public DadosMetereologicosDTO updateDadosMeterologicos(
            String cidade,
            LocalDate data,
            TipoTurno turno,
            DadosMetereologicosDTO dadosMetereologicosDTO) {

            Optional<DadosMetereologicos> dadosMetereologicosEntity = dadosMetereologicosRepository.findByCidadeAndDataAndTurno(cidade,data,turno);

            updateDadosMetereologicosUseCaseImp.execute(dadosMetereologicosDTO);

            if (dadosMetereologicosEntity.isPresent()) {
                DadosMetereologicos existingDadosMetereologicos = dadosMetereologicosEntity.get();

                String cidadeUpdate = dadosMetereologicosDTO.cidade() == null ? existingDadosMetereologicos.getCidade() : dadosMetereologicosDTO.cidade();
                LocalDate dataUpdate = dadosMetereologicosDTO.data() == null ? existingDadosMetereologicos.getData() : dadosMetereologicosDTO.data();
                TipoTurno turnoUpdate = dadosMetereologicosDTO.turno() == null ? existingDadosMetereologicos.getTurno() : dadosMetereologicosDTO.turno();
                String tempMinUpdate = dadosMetereologicosDTO.tempMin() == null ? existingDadosMetereologicos.getTempMin() : dadosMetereologicosDTO.tempMin();
                String tempMaxUpdate = dadosMetereologicosDTO.tempMax() == null ? existingDadosMetereologicos.getTempMax() : dadosMetereologicosDTO.tempMax();
                TipoClima climaUpdate = dadosMetereologicosDTO.clima() == null ? existingDadosMetereologicos.getClima() : dadosMetereologicosDTO.clima();
                String precipitacaoUpdate = dadosMetereologicosDTO.precipitacao() == null ? existingDadosMetereologicos.getPrecipitacao() : dadosMetereologicosDTO.precipitacao();
                String umidadeUpdate = dadosMetereologicosDTO.umidade() == null ? existingDadosMetereologicos.getUmidade() : dadosMetereologicosDTO.umidade();
                String velDoVentoUpdate = dadosMetereologicosDTO.velDoVento() == null ? existingDadosMetereologicos.getVelDoVento() : dadosMetereologicosDTO.velDoVento();

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

                existingDadosMetereologicos.setCidade(cidadeUpdate);
                existingDadosMetereologicos.setData(dataUpdate);
                existingDadosMetereologicos.setTurno(turnoUpdate);
                existingDadosMetereologicos.setTempMin(tempMinUpdate);
                existingDadosMetereologicos.setTempMax(tempMaxUpdate);
                existingDadosMetereologicos.setClima(climaUpdate);
                existingDadosMetereologicos.setPrecipitacao(precipitacaoUpdate);
                existingDadosMetereologicos.setUmidade(umidadeUpdate);
                existingDadosMetereologicos.setVelDoVento(velDoVentoUpdate);

                DadosMetereologicos dadosMetereologicosSaved = dadosMetereologicosRepository.save(existingDadosMetereologicos);
                return entityMapper.toDadosMetereologicosDTO(dadosMetereologicosSaved);
            } else {
                throw new ValidacaoException("Evento climático não localizado.");
            }
    }
}
