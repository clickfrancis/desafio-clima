package temp.desafio.api.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.infrastructure.service.DadosMetereologicosService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateDadosMetereologicosUseCaseImp implements CreateDadosMetereologicosUseCase{

    @Autowired
    private final List<Validacao> validacoes;

    @Override
    public DadosMetereologicos execute(DadosMetereologicos dadosMetereologicos) {
        validacoes.forEach(validacao -> validacao.validar(dadosMetereologicos));
        return dadosMetereologicos;
    }
}
