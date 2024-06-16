package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.entities.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;

public class CreateDadosMetereologicosUseCaseImp implements CreateDadosMetereologicosUseCase{

    private final IDadosMetereologicos dadosMetereologicosGateway;

    public CreateDadosMetereologicosUseCaseImp(IDadosMetereologicos dadosMetereologicosGateway) {
        this.dadosMetereologicosGateway = dadosMetereologicosGateway;
    }

    @Override
    public DadosMetereologicos execute(DadosMetereologicos dadosMetereologicos) {
        return dadosMetereologicosGateway.createDadosMetereologicos(dadosMetereologicos);
    }
}
