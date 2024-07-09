package temp.desafio.api.infrastructure.mappers;

import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicos;

@Component
public class DadosMetereologicosEntityMapper {
    public DadosMetereologicos toDadosMetereologicos(DadosMetereologicosDTO dadosMetereologicosDTO){
        return new DadosMetereologicos(null,
                dadosMetereologicosDTO.cidade(),
                dadosMetereologicosDTO.data(),
                dadosMetereologicosDTO.tempMin(),
                dadosMetereologicosDTO.tempMax(),
                dadosMetereologicosDTO.turno(),
                dadosMetereologicosDTO.clima(),
                dadosMetereologicosDTO.precipitacao(),
                dadosMetereologicosDTO.umidade(),
                dadosMetereologicosDTO.velDoVento()
        );
    }

    public DadosMetereologicosDTO toDadosMetereologicosDTO(DadosMetereologicos dadosMetereologicos){
        return new DadosMetereologicosDTO(
                dadosMetereologicos.getCidade(),
                dadosMetereologicos.getData(),
                dadosMetereologicos.getTempMin(),
                dadosMetereologicos.getTempMax(),
                dadosMetereologicos.getTurno(),
                dadosMetereologicos.getClima(),
                dadosMetereologicos.getPrecipitacao(),
                dadosMetereologicos.getUmidade(),
                dadosMetereologicos.getVelDoVento()
        );
    }
}
