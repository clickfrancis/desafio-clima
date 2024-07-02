package temp.desafio.api.core.usecase.validadores;

import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.exceptions.ValidacaoException;

@Component
public class ValidadorDeCamposNulosUpdateCase implements ValidacaoUpdateDadosMetereologicos {

    public void validar(DadosMetereologicos dados) {
        if(
            dados.cidade() == null &&
            dados.data() == null &&
            dados.tempMin() == null &&
            dados.tempMax() == null &&
            dados.turno() == null &&
            dados.clima() == null &&
            dados.precipitacao() == null &&
            dados.umidade() == null &&
            dados.velDoVento() == null
        ) {
            throw new ValidacaoException("Não há atualização para fazer, pois nenhum campo foi preenchido!");
        }
    }
}
