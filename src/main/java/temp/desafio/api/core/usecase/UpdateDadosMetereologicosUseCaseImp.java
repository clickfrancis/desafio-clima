package temp.desafio.api.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.usecase.validadores.ValidacaoUpdateDadosMetereologicos;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateDadosMetereologicosUseCaseImp implements UpdateDadosMetereologicosUseCase {

    @Autowired
    private final List<ValidacaoUpdateDadosMetereologicos> validacoes;

    @Override
    public DadosMetereologicos execute(DadosMetereologicos dadosMetereologicos) {
        validacoes.forEach(validacaoUpdateDadosMetereologicos -> validacaoUpdateDadosMetereologicos.validar(dadosMetereologicos));
        return dadosMetereologicos;
    }
}
