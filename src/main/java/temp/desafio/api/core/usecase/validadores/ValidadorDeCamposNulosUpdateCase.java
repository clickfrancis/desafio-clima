package temp.desafio.api.core.usecase.validadores;

import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.exceptions.ValidacaoException;

@Component
public class ValidadorDeCamposNulosUpdateCase implements ValidacaoUpdateDadosMetereologicos {

    public void validar(DadosMetereologicosDTO dados) {
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

        if (
            dados.cidade() == null ||
            dados.data() == null ||
            dados.turno() == null
        ) {
            throw new ValidacaoException("Campos cidade, data e turno precisam ser informados.");
        }
    }
}
