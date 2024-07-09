package temp.desafio.api.core.usecase;

import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.infrastructure.service.DadosMetereologicosService;
import java.util.List;

@Component
public class GetDadosMetereologicosUseCaseImp implements GetDadosMetereologicosUseCase {

    private final DadosMetereologicosService dadosMetereologicosService = new DadosMetereologicosService();

    @Override
    public List<DadosMetereologicosDTO> execute() {
        return dadosMetereologicosService.getAllDadosMetereologicos();
    }

    @Override
    public DadosMetereologicosDTO executeByCity(String cidade) {
        return dadosMetereologicosService.findByCidade(cidade);
    }


}
