package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;

public interface UpdateDadosMetereologicosUseCase {

    DadosMetereologicosDTO execute(DadosMetereologicosDTO dadosMetereologicosDTO);
}
