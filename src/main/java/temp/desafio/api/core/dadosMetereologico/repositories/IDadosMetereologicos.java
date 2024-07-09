package temp.desafio.api.core.dadosMetereologico.repositories;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.enums.TipoTurno;
import java.time.LocalDate;
import java.util.List;

public interface IDadosMetereologicos {

    DadosMetereologicosDTO createDadosMetereologicos(DadosMetereologicosDTO dadosMetereologicosDTO);

    DadosMetereologicosDTO findByCidade(String cidade);

    List<DadosMetereologicosDTO> getAllDadosMetereologicos();

    void deleteDadosMeterologicos(String cidade, LocalDate data, TipoTurno turno);

    DadosMetereologicosDTO updateDadosMeterologicos(
            String cidade,
            LocalDate data,
            TipoTurno turno,
            DadosMetereologicosDTO dadosMetereologicosDTO
    );
}
