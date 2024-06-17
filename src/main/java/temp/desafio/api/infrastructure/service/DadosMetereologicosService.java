package temp.desafio.api.infrastructure.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.infrastructure.mappers.DadosMetereologicosEntityMapper;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicosEntity;
import temp.desafio.api.infrastructure.persistence.repositories.DadosMetereologicosRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
@RequiredArgsConstructor
public class DadosMetereologicosService implements IDadosMetereologicos {

    @Autowired
    private final DadosMetereologicosRepository dadosMetereologicosRepository;

    @Autowired
    private final DadosMetereologicosEntityMapper entityMapper;

    @Override
    public DadosMetereologicos createDadosMetereologicos(DadosMetereologicos dadosMetereologicos){
        DadosMetereologicosEntity dadosMetereologicosEntity = entityMapper.toDadosMetereologicosEntity(dadosMetereologicos);
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
        DadosMetereologicosEntity dadosMetereologicosEntity = entityMapper.toDadosMetereologicosEntity(dadosMetereologicos);
        dadosMetereologicosRepository.delete(dadosMetereologicosEntity);
    }

    @Override
    public DadosMetereologicos updateDadosMeterologicos(DadosMetereologicos dadosMetereologicos) {
        return null;
    }
}
