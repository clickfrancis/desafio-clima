package temp.desafio.api.core.usecase;

import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.exceptions.ValidacaoException;

@Component
public class ValidadorTurnoNaoVazio implements Validacao{

    @Override
    public void validar(DadosMetereologicos dados) {
        if(dados.turno() != null && dados.data() != null) {
            throw new ValidacaoException("Já existe um turno cadastrado!");
        }
    }
}
