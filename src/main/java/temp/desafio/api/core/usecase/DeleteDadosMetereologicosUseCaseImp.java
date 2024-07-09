package temp.desafio.api.core.usecase;

import org.springframework.stereotype.Component;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.core.exceptions.ValidacaoException;
import java.time.LocalDate;

@Component
public class DeleteDadosMetereologicosUseCaseImp implements DeleteDadosMetereologicosUseCase {

    @Override
    public void execute(String cidade, LocalDate data, TipoTurno turno) {
        if(
            cidade.isEmpty() ||
            data == null ||
            turno == null
        ) {
            throw new ValidacaoException("Campo deve ser prenchido");
        }
    }
}
