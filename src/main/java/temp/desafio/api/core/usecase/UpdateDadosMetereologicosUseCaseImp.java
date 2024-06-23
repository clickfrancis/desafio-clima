package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.infrastructure.service.DadosMetereologicosService;

public class UpdateDadosMetereologicosUseCaseImp implements UpdateDadosMetereologicosUseCase {

    private final DadosMetereologicosService dadosMetereologicosService;

    public UpdateDadosMetereologicosUseCaseImp(DadosMetereologicosService dadosMetereologicosService) {
        this.dadosMetereologicosService = dadosMetereologicosService;
    }

    @Override
    public DadosMetereologicos execute(DadosMetereologicos dadosMetereologicos) {
        return dadosMetereologicosService.updateDadosMeterologicos(
                dadosMetereologicos.cidade(),
                dadosMetereologicos.data(),
                dadosMetereologicos.turno(),
                dadosMetereologicos
        );

    }

}
