package temp.desafio.api.infrastructure.mappers;

import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.entities.DadosMetereologicos;
import temp.desafio.api.infrastructure.persistence.DadosMetereologicosEntity;

@Component
public class DadosMetereologicosEntityMapper {
    public DadosMetereologicosEntity toEntity(DadosMetereologicos dadosMetereologicos){
        return new DadosMetereologicosEntity(
                dadosMetereologicos.id(),
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
                dadosMetereologicosEntity.getId(),
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
