package temp.desafio.api.core.usecase;

import temp.desafio.api.core.enums.TipoTurno;

import java.time.LocalDate;

public interface DeleteDadosMetereologicosUseCase {

    void execute(String cidade, LocalDate data, TipoTurno turno);
}
