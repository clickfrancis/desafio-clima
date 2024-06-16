package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.entities.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;

public class DeleteDadosMetereologicosUseCaseImp implements DeleteDadosMetereologicosUseCase {

    private final IDadosMetereologicos dadosMetereologicosGateway;

    public DeleteDadosMetereologicosUseCaseImp(IDadosMetereologicos dadosMetereologicosGateway) {
        this.dadosMetereologicosGateway = dadosMetereologicosGateway;
    }

    @Override
    public DadosMetereologicos execute(DadosMetereologicos dadosMetereologicos) {
        dadosMetereologicosGateway.deleteDadosMeterologicos(dadosMetereologicos);
        return dadosMetereologicos;
    }

}
