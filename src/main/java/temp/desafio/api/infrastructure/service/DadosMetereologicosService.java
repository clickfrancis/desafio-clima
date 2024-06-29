package temp.desafio.api.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.core.usecase.CreateDadosMetereologicosUseCaseImp;
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
    private final DadosMetereologicosRepository dadosMetereologicosRepository;

    @Autowired
    private final DadosMetereologicosEntityMapper entityMapper;

    @Autowired
    private CreateDadosMetereologicosUseCaseImp createDadosMetereologicosUseCase;

    @Override
    public DadosMetereologicos createDadosMetereologicos(DadosMetereologicos dadosMetereologicos){
        createDadosMetereologicosUseCase.execute(dadosMetereologicos);
        DadosMetereologicosEntity dadosMetereologicosEntity = entityMapper.toDadosMetereologicosEntity(dadosMetereologicos);
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
    public void deleteDadosMeterologicos(
            String cidade,
            LocalDate data,
            TipoTurno turno
    ) {
        Optional<DadosMetereologicosEntity> optionalDadosMetereologicosEntity = dadosMetereologicosRepository.findByCidadeAndDataAndTurno(cidade, data, turno);
        if (optionalDadosMetereologicosEntity.isPresent()){
            DadosMetereologicosEntity dadosMetereologicosEntity = optionalDadosMetereologicosEntity.get();
            dadosMetereologicosRepository.delete(dadosMetereologicosEntity);
        }
    }

    @Override
    public DadosMetereologicos updateDadosMeterologicos(
            String cidade,
            LocalDate data,
            TipoTurno turno,
            DadosMetereologicos dadosMetereologicos) {
            Optional<DadosMetereologicosEntity> dadosMetereologicosEntity = dadosMetereologicosRepository.findByCidadeAndDataAndTurno(cidade, data, turno);
        if (dadosMetereologicosEntity.isPresent()) {
            DadosMetereologicosEntity existingDadosMetereologicosEntity = dadosMetereologicosEntity.get();
            existingDadosMetereologicosEntity.setCidade(dadosMetereologicos.cidade());
            existingDadosMetereologicosEntity.setData(dadosMetereologicos.data());
            existingDadosMetereologicosEntity.setClima(dadosMetereologicos.clima());
            existingDadosMetereologicosEntity.setTurno(dadosMetereologicos.turno());
            existingDadosMetereologicosEntity.setPrecipitacao(dadosMetereologicos.precipitacao());
            existingDadosMetereologicosEntity.setTurno(dadosMetereologicos.turno());
            existingDadosMetereologicosEntity.setTempMax(dadosMetereologicos.tempMax());            existingDadosMetereologicosEntity.setTempMin(dadosMetereologicos.tempMin());
            existingDadosMetereologicosEntity.setUmidade(dadosMetereologicos.umidade());
            existingDadosMetereologicosEntity.setVelDoVento(dadosMetereologicos.velDoVento());
            dadosMetereologicosRepository.save(existingDadosMetereologicosEntity);
            return entityMapper.toDadosMetereologicos(existingDadosMetereologicosEntity);
        } else {
            throw new RuntimeException("DadosMetereologicos not found for cidade: " + cidade);
        }
    }
}
