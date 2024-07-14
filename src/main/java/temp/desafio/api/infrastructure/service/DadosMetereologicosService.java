package temp.desafio.api.infrastructure.service;

import lombok.AllArgsConstructor;
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
public class DadosMetereologicosService implements IDadosMetereologicos {

    private final DadosMetereologicosRepository dadosMetereologicosRepository;
    private final DadosMetereologicosEntityMapper entityMapper;
    private final CreateDadosMetereologicosUseCaseImp createDadosMetereologicosUseCase;
    private final UpdateDadosMetereologicosUseCaseImp updateDadosMetereologicosUseCase;
    private final DeleteDadosMetereologicosUseCaseImp deleteDadosMetereologicosUseCase;


    public DadosMetereologicosService(
            DadosMetereologicosRepository dadosMetereologicosRepository,
            DadosMetereologicosEntityMapper entityMapper,
            CreateDadosMetereologicosUseCaseImp createDadosMetereologicosUseCase,
            UpdateDadosMetereologicosUseCaseImp updateDadosMetereologicosUseCase,
            DeleteDadosMetereologicosUseCaseImp deleteDadosMetereologicosUseCase
    ) {
        this.dadosMetereologicosRepository = dadosMetereologicosRepository;
        this.entityMapper = entityMapper;
        this.createDadosMetereologicosUseCase = createDadosMetereologicosUseCase;
        this.updateDadosMetereologicosUseCase = updateDadosMetereologicosUseCase;
        this.deleteDadosMetereologicosUseCase = deleteDadosMetereologicosUseCase;
    }

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
    public DadosMetereologicosDTO updateDadosMeterologicos(DadosMetereologicosDTO dadosMetereologicosDTO) {

        return updateDadosMetereologicosUseCase.execute(dadosMetereologicosDTO);
    }
}
