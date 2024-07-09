package temp.desafio.api.core.usecase;

import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.usecase.validadores.ValidacaoUpdateDadosMetereologicos;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateDadosMetereologicosUseCaseImp implements UpdateDadosMetereologicosUseCase {

    private final List<ValidacaoUpdateDadosMetereologicos> validacoes = new ArrayList<>();

    @Override
    public DadosMetereologicosDTO execute(DadosMetereologicosDTO dadosMetereologicosDTO) {
        validacoes.forEach(validacaoUpdateDadosMetereologicos -> validacaoUpdateDadosMetereologicos.validar(dadosMetereologicosDTO));
        return dadosMetereologicosDTO;
    }
}
