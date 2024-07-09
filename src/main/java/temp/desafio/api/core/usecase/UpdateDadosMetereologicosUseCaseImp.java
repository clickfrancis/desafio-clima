package temp.desafio.api.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.usecase.validadores.ValidacaoUpdateDadosMetereologicos;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateDadosMetereologicosUseCaseImp implements UpdateDadosMetereologicosUseCase {

    @Autowired
    private final List<ValidacaoUpdateDadosMetereologicos> validacoes;

    @Override
    public DadosMetereologicosDTO execute(DadosMetereologicosDTO dadosMetereologicosDTO) {
        validacoes.forEach(validacaoUpdateDadosMetereologicos -> validacaoUpdateDadosMetereologicos.validar(dadosMetereologicosDTO));
        return dadosMetereologicosDTO;
    }
}
