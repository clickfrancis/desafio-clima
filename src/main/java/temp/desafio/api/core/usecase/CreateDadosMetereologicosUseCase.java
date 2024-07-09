package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;

public interface CreateDadosMetereologicosUseCase {

    DadosMetereologicosDTO execute(DadosMetereologicosDTO dadosMetereologicosDTO);
}
