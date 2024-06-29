package temp.desafio.api.core.usecase;


import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.exceptions.ValidacaoException;

@Component
public class ValidadorDeCamposNulos implements Validacao{

    @Override
    public void validar(DadosMetereologicos dados) {
        if(
            dados.cidade() == null ||
            dados.data() == null ||
            dados.turno() == null ||
            dados.clima() == null ||
            dados.tempMin() == null ||
            dados.tempMax() == null
        ) {
            throw new ValidacaoException("Campo não pode está vazio!");
        }
    }
}
