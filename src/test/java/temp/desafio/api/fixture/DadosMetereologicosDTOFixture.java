package temp.desafio.api.fixture;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.enums.TipoClima;
import temp.desafio.api.core.enums.TipoTurno;

import java.time.LocalDate;

public class DadosMetereologicosDTOFixture {

    public static DadosMetereologicosDTO createDadosMetereologicosDTO() {
        return new DadosMetereologicosDTO(
                "São Paulo",
                LocalDate.of(2024, 7, 11),
                "20°C",
                "30°C",
                TipoTurno.MANHA,
                TipoClima.ENSOLARADO,
                "0.5 mm",
                "70%",
                "10 km/h"
        );
    }

    public static DadosMetereologicosDTO createDadosMetereologicosDTOExistente() {
        return new DadosMetereologicosDTO(
                "São Paulo",
                LocalDate.of(2024, 7, 11),
                "20°C",
                "30°C",
                TipoTurno.MANHA,
                TipoClima.ENSOLARADO,
                "0.5 mm",
                "70%",
                "10 km/h"
        );
    }

    public static DadosMetereologicosDTO createDadosMetereologicosDTONull() {
        return new DadosMetereologicosDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}