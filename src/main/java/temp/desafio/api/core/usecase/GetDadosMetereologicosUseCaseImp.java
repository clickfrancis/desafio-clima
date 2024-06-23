package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.infrastructure.service.DadosMetereologicosService;

import java.util.List;

public class GetDadosMetereologicosUseCaseImp implements GetDadosMetereologicosUseCase {

    private final DadosMetereologicosService dadosMetereologicosService;

    public GetDadosMetereologicosUseCaseImp(DadosMetereologicosService dadosMetereologicosService) {
        this.dadosMetereologicosService = dadosMetereologicosService;
    }

    @Override
    public List<DadosMetereologicos> execute() {
        return dadosMetereologicosService.getAllDadosMetereologicos();
    }

    @Override
    public DadosMetereologicos executeByCity(String cidade) {
        return dadosMetereologicosService.findByCidade(cidade);
    }


}
