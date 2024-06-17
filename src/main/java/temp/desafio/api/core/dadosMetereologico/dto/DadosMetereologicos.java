package temp.desafio.api.core.dadosMetereologico.dto;

import temp.desafio.api.core.enums.TipoClima;
import temp.desafio.api.core.enums.TipoTurno;

import java.util.Date;

public record DadosMetereologicos(
        String cidade,
        Date data,
        double tempMin,
        double tempMax,
        TipoTurno turno,
        TipoClima clima,
        double precipitacao,
        double umidade,
        double velDoVento
) {
}
