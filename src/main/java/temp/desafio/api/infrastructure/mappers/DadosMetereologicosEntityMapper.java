package temp.desafio.api.infrastructure.mappers;

import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicosEntity;

@Component
public class DadosMetereologicosEntityMapper {
    public DadosMetereologicosEntity toDadosMetereologicosEntity(DadosMetereologicos dadosMetereologicos){
        return new DadosMetereologicosEntity(null,
                dadosMetereologicos.cidade(),
                dadosMetereologicos.data(),
                dadosMetereologicos.tempMin(),
                dadosMetereologicos.tempMax(),
                dadosMetereologicos.turno(),
                dadosMetereologicos.clima(),
                dadosMetereologicos.precipitacao(),
                dadosMetereologicos.umidade(),
                dadosMetereologicos.velDoVento()
        );
    }

    public DadosMetereologicos toDadosMetereologicos(DadosMetereologicosEntity dadosMetereologicosEntity){
        return new DadosMetereologicos(
                dadosMetereologicosEntity.getCidade(),
                dadosMetereologicosEntity.getData(),
                dadosMetereologicosEntity.getTempMin(),
                dadosMetereologicosEntity.getTempMax(),
                dadosMetereologicosEntity.getTurno(),
                dadosMetereologicosEntity.getClima(),
                dadosMetereologicosEntity.getPrecipitacao(),
                dadosMetereologicosEntity.getUmidade(),
                dadosMetereologicosEntity.getVelDoVento()
        );
    }
}
