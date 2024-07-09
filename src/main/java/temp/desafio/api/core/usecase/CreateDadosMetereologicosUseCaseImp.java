package temp.desafio.api.core.usecase;

import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.usecase.validadores.ValidacaoCreateDadosMetereologicos;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateDadosMetereologicosUseCaseImp implements CreateDadosMetereologicosUseCase{

    private final List<ValidacaoCreateDadosMetereologicos> validacoes = new ArrayList<>();

    @Override
    public DadosMetereologicosDTO execute(DadosMetereologicosDTO dadosMetereologicosDTO) {
        validacoes.forEach(validacaoCreateDadosMetereologicos -> validacaoCreateDadosMetereologicos.validar(dadosMetereologicosDTO));
        return dadosMetereologicosDTO;
    }
}
