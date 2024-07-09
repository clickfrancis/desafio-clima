package temp.desafio.api.core.usecase.validadores;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.exceptions.ValidacaoException;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicos;
import temp.desafio.api.infrastructure.persistence.repositories.DadosMetereologicosRepository;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ValidadorTurnoNaoVazioCreateCase implements ValidacaoCreateDadosMetereologicos {

    @Autowired
    private DadosMetereologicosRepository repositoryGateway;

    @Override
    public void validar(DadosMetereologicosDTO dadosMetereologicosDTO) {
        Optional<DadosMetereologicos> dados =
                repositoryGateway.findByCidadeAndDataAndTurno(dadosMetereologicosDTO.cidade(), dadosMetereologicosDTO.data(), dadosMetereologicosDTO.turno());
        if (dados.isPresent()) {
            throw new ValidacaoException("Já existe um evento climatico cadastrado para esse turno.");
        }

    }
}
