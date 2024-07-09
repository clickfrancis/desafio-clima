package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import java.util.List;

public interface GetDadosMetereologicosUseCase {

    List<DadosMetereologicosDTO> execute();

    DadosMetereologicosDTO executeByCity(String cidade);
}
