package temp.desafio.api.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import temp.desafio.api.core.dadosMetereologico.entities.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.infrastructure.mappers.DadosMetereologicosEntityMapper;
import temp.desafio.api.infrastructure.persistence.DadosMetereologicosEntity;
import temp.desafio.api.infrastructure.persistence.DadosMetereologicosRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
@RequiredArgsConstructor
public class DadosMetereologicosService implements IDadosMetereologicos {

    private final DadosMetereologicosRepository dadosMetereologicosRepository;

    private final DadosMetereologicosEntityMapper entityMapper;



    @Override
    public DadosMetereologicos createDadosMetereologicos(DadosMetereologicos dadosMetereologicos){
        DadosMetereologicosEntity dadosMetereologicosEntity = entityMapper.toEntity(dadosMetereologicos);
        DadosMetereologicosEntity novoDadosMetereologicos = dadosMetereologicosRepository.save(dadosMetereologicosEntity);
        return entityMapper.toDadosMetereologicos(novoDadosMetereologicos);
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
    public void deleteDadosMeterologicos(DadosMetereologicos dadosMetereologicos) {
        DadosMetereologicosEntity dadosMetereologicosEntity = entityMapper.toEntity(dadosMetereologicos);
        dadosMetereologicosRepository.delete(dadosMetereologicosEntity);
    }

    @Override
    public DadosMetereologicos updateDadosMeterologicos(DadosMetereologicos dadosMetereologicos) {
        return null;
    }
}
