package temp.desafio.api.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.infrastructure.service.DadosMetereologicosService;

@RequiredArgsConstructor
public class UpdateDadosMetereologicosUseCaseImp implements UpdateDadosMetereologicosUseCase {

    @Autowired
    private final DadosMetereologicosService dadosMetereologicosService;

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
