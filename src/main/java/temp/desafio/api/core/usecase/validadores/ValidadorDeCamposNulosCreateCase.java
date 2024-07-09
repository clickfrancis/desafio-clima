package temp.desafio.api.core.usecase.validadores;


import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.exceptions.ValidacaoException;

@Component
public class ValidadorDeCamposNulosCreateCase implements ValidacaoCreateDadosMetereologicos {

    @Override
    public void validar(DadosMetereologicosDTO dados) {
        if(
            dados.cidade() == null ||
            dados.data() == null ||
            dados.turno() == null ||
            dados.clima() == null ||
            dados.tempMin() == null ||
            dados.tempMax() == null
        ) {
            throw new ValidacaoException("Campos obrigatorios não podem está vazios!");
        }
    }
}
