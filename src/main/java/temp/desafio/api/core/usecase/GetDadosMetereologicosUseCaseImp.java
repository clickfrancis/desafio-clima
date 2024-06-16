package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.entities.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;

import java.util.List;

public class GetDadosMetereologicosUseCaseImp implements GetDadosMetereologicosUseCase {


    private final IDadosMetereologicos dadosMetereologicosGateway;

    public GetDadosMetereologicosUseCaseImp(IDadosMetereologicos dadosMetereologicosGateway) {
        this.dadosMetereologicosGateway = dadosMetereologicosGateway;
    }

    @Override
    public List<DadosMetereologicos> execute() {
        return dadosMetereologicosGateway.getAllDadosMetereologicos();
    }

    @Override
    public DadosMetereologicos executeByCity(String cidade) {
        return dadosMetereologicosGateway.findByCidade(cidade);
    }


}
