package temp.desafio.api.core.usecase.validadores;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.infrastructure.persistence.repositories.DadosMetereologicosRepository;

@Component
@RequiredArgsConstructor
public class ValidadorTurnoNaoVazioUpdateCase {

    @Autowired
    private final DadosMetereologicosRepository repositoryGateway;

    public void validar(DadosMetereologicosDTO dadosMetereologicosDTO) {
    }
}
