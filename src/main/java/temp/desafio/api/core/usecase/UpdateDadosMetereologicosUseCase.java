package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;

public interface UpdateDadosMetereologicosUseCase {

    DadosMetereologicos execute(DadosMetereologicos dadosMetereologicos);
}
