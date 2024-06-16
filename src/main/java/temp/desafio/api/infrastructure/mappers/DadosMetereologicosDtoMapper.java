package temp.desafio.api.infrastructure.mappers;

import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.entities.DadosMetereologicos;
import temp.desafio.api.infrastructure.dto.DadosMetereologicosDto;

@Component
public class DadosMetereologicosDtoMapper {

    public DadosMetereologicosDto toDto(DadosMetereologicos dadosMetereologicos){
        return new DadosMetereologicosDto(
                dadosMetereologicos.cidade(),
                dadosMetereologicos.data(),
                dadosMetereologicos.tempMin(),
                dadosMetereologicos.tempMax(),
                dadosMetereologicos.turno(),
                dadosMetereologicos.clima(),
                dadosMetereologicos.precipitacao(),
                dadosMetereologicos.umidade(),
                dadosMetereologicos.velDoVento());
    }

    public DadosMetereologicos toDomain(DadosMetereologicosDto dadosMetereologicosDto){
        return new DadosMetereologicos(
                null,
                dadosMetereologicosDto.cidade(),
                dadosMetereologicosDto.data(),
                dadosMetereologicosDto.tempMin(),
                dadosMetereologicosDto.tempMax(),
                dadosMetereologicosDto.turno(),
                dadosMetereologicosDto.clima(),
                dadosMetereologicosDto.precipitacao(),
                dadosMetereologicosDto.umidade(),
                dadosMetereologicosDto.velDoVento()
        );
    }
}
