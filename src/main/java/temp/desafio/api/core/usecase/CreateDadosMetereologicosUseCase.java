package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.entities.DadosMetereologicos;

public interface CreateDadosMetereologicosUseCase {

    DadosMetereologicos execute(DadosMetereologicos dadosMetereologicos);
}
