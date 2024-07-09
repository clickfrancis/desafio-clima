package temp.desafio.api.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.usecase.validadores.ValidacaoCreateDadosMetereologicos;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateDadosMetereologicosUseCaseImp implements CreateDadosMetereologicosUseCase{

    @Autowired
    private final List<ValidacaoCreateDadosMetereologicos> validacoes;

    @Override
    public DadosMetereologicosDTO execute(DadosMetereologicosDTO dadosMetereologicosDTO) {
        validacoes.forEach(validacaoCreateDadosMetereologicos -> validacaoCreateDadosMetereologicos.validar(dadosMetereologicosDTO));
        return dadosMetereologicosDTO;
    }
}
