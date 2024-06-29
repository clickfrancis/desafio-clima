package temp.desafio.api.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.infrastructure.service.DadosMetereologicosService;


@RequiredArgsConstructor
public class DeleteDadosMetereologicosUseCaseImp implements DeleteDadosMetereologicosUseCase {

    @Autowired
    private final DadosMetereologicosService dadosMetereologicosService;

    @Override
    public void execute(DadosMetereologicos dadosMetereologicos) {
        dadosMetereologicosService.deleteDadosMeterologicos(
                dadosMetereologicos.cidade(),
                dadosMetereologicos.data(),
                dadosMetereologicos.turno()
        );
    }
}
