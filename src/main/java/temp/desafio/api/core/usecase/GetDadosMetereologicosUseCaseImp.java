package temp.desafio.api.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.infrastructure.service.DadosMetereologicosService;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetDadosMetereologicosUseCaseImp implements GetDadosMetereologicosUseCase {

    @Autowired
    private final DadosMetereologicosService dadosMetereologicosService;

    @Override
    public List<DadosMetereologicos> execute() {
        return dadosMetereologicosService.getAllDadosMetereologicos();
    }

    @Override
    public DadosMetereologicos executeByCity(String cidade) {
        return dadosMetereologicosService.findByCidade(cidade);
    }


}
