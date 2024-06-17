package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;

public class UpdateDadosMetereologicosUseCaseImp implements UpdateDadosMetereologicosUseCase {

    private final IDadosMetereologicos dadosMetereologicosGateway;

    public UpdateDadosMetereologicosUseCaseImp(IDadosMetereologicos dadosMetereologicosGateway) {
        this.dadosMetereologicosGateway = dadosMetereologicosGateway;
    }

    @Override
    public DadosMetereologicos execute(DadosMetereologicos dadosMetereologicos) {
        return dadosMetereologicosGateway.updateDadosMeterologicos(dadosMetereologicos);
    }

}
