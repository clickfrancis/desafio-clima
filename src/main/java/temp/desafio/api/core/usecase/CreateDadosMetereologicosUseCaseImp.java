package temp.desafio.api.core.usecase;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.dadosMetereologico.repositories.IDadosMetereologicos;
import temp.desafio.api.infrastructure.service.DadosMetereologicosService;


public class CreateDadosMetereologicosUseCaseImp implements CreateDadosMetereologicosUseCase{

    private final DadosMetereologicosService dadosMetereologicosService;

    public CreateDadosMetereologicosUseCaseImp(DadosMetereologicosService dadosMetereologicosService) {
        this.dadosMetereologicosService = dadosMetereologicosService;
    }


    @Override
    public DadosMetereologicos execute(DadosMetereologicos dadosMetereologicos) {
        return dadosMetereologicosService.createDadosMetereologicos(dadosMetereologicos);
    }
}
