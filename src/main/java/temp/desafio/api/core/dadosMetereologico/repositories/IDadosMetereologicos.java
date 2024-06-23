package temp.desafio.api.core.dadosMetereologico.repositories;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.enums.TipoTurno;
import java.time.LocalDate;
import java.util.List;

public interface IDadosMetereologicos {

    DadosMetereologicos createDadosMetereologicos(DadosMetereologicos dadosMetereologicos);

    DadosMetereologicos findByCidade(String cidade);

    List<DadosMetereologicos> getAllDadosMetereologicos();

    void deleteDadosMeterologicos(String cidade, LocalDate data, TipoTurno turno);

    DadosMetereologicos updateDadosMeterologicos(
            String cidade,
            LocalDate data,
            TipoTurno turno,
            DadosMetereologicos dadosMetereologicos
    );
}
