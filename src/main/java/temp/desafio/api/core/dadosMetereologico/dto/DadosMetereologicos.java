package temp.desafio.api.core.dadosMetereologico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import temp.desafio.api.core.enums.TipoClima;
import temp.desafio.api.core.enums.TipoTurno;
import java.time.LocalDate;

public record DadosMetereologicos(
        String cidade,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
        LocalDate data,
        double tempMin,
        double tempMax,
        TipoTurno turno,
        TipoClima clima,
        double precipitacao,
        double umidade,
        double velDoVento
) {
}
