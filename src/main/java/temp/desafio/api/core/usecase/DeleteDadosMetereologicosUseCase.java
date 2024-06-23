package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;

public interface DeleteDadosMetereologicosUseCase {

    void execute(DadosMetereologicos dadosMetereologicos);
}
