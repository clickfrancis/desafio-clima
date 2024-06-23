package temp.desafio.api.core.usecase;

import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.infrastructure.service.DadosMetereologicosService;

public class DeleteDadosMetereologicosUseCaseImp implements DeleteDadosMetereologicosUseCase {

    private final DadosMetereologicosService dadosMetereologicosService;

    public DeleteDadosMetereologicosUseCaseImp (
            DadosMetereologicosService dadosMetereologicosService
    ) {
        this.dadosMetereologicosService = dadosMetereologicosService;
    }

    @Override
    public void execute(DadosMetereologicos dadosMetereologicos) {
        dadosMetereologicosService.deleteDadosMeterologicos(
                dadosMetereologicos.cidade(),
                dadosMetereologicos.data(),
                dadosMetereologicos.turno()
        );
    }
}
