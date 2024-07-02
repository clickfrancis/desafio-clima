package temp.desafio.api.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.usecase.validadores.ValidacaoCreateDadosMetereologicos;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateDadosMetereologicosUseCaseImp implements CreateDadosMetereologicosUseCase{

    @Autowired
    private final List<ValidacaoCreateDadosMetereologicos> validacoes;

    @Override
    public DadosMetereologicos execute(DadosMetereologicos dadosMetereologicos) {
        validacoes.forEach(validacaoCreateDadosMetereologicos -> validacaoCreateDadosMetereologicos.validar(dadosMetereologicos));
        return dadosMetereologicos;
    }
}
