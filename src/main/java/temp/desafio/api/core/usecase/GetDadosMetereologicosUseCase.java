package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import java.util.List;

public interface GetDadosMetereologicosUseCase {

    List<DadosMetereologicos> execute();

    DadosMetereologicos executeByCity(String cidade);
}
