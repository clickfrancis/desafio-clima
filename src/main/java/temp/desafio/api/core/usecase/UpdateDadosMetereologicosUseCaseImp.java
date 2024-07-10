package temp.desafio.api.core.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.enums.TipoClima;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.core.usecase.validadores.ValidacaoUpdateDadosMetereologicos;
import temp.desafio.api.infrastructure.mappers.DadosMetereologicosEntityMapper;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicos;
import temp.desafio.api.infrastructure.persistence.repositories.DadosMetereologicosRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateDadosMetereologicosUseCaseImp implements UpdateDadosMetereologicosUseCase {

    private final List<ValidacaoUpdateDadosMetereologicos> validacoes = new ArrayList<>();

    @Autowired
    private final DadosMetereologicosRepository dadosMetereologicosRepository;

    private final DadosMetereologicosEntityMapper entityMapper;

    @Override
    public DadosMetereologicosDTO execute(DadosMetereologicosDTO dadosMetereologicosDTO) {
        validacoes.forEach(validacaoUpdateDadosMetereologicos -> validacaoUpdateDadosMetereologicos.validar(dadosMetereologicosDTO));

        Optional<DadosMetereologicos> dadosMetereologicosEntity =
                dadosMetereologicosRepository.findByCidadeAndDataAndTurno(
                        dadosMetereologicosDTO.cidade(),
                        dadosMetereologicosDTO.data(),
                        dadosMetereologicosDTO.turno()
                );

        if (dadosMetereologicosEntity.isPresent()) {

            DadosMetereologicos existingDadosMetereologicos = dadosMetereologicosEntity.get();

            String cidadeUpdate = dadosMetereologicosDTO.cidade() == null ? existingDadosMetereologicos.getCidade() : dadosMetereologicosDTO.cidade();
            LocalDate dataUpdate = dadosMetereologicosDTO.data() == null ? existingDadosMetereologicos.getData() : dadosMetereologicosDTO.data();
            TipoTurno turnoUpdate = dadosMetereologicosDTO.turno() == null ? existingDadosMetereologicos.getTurno() : dadosMetereologicosDTO.turno();
            String tempMinUpdate = dadosMetereologicosDTO.tempMin() == null ? existingDadosMetereologicos.getTempMin() : dadosMetereologicosDTO.tempMin();
            String tempMaxUpdate = dadosMetereologicosDTO.tempMax() == null ? existingDadosMetereologicos.getTempMax() : dadosMetereologicosDTO.tempMax();
            TipoClima climaUpdate = dadosMetereologicosDTO.clima() == null ? existingDadosMetereologicos.getClima() : dadosMetereologicosDTO.clima();
            String precipitacaoUpdate = dadosMetereologicosDTO.precipitacao() == null ? existingDadosMetereologicos.getPrecipitacao() : dadosMetereologicosDTO.precipitacao();
            String umidadeUpdate = dadosMetereologicosDTO.umidade() == null ? existingDadosMetereologicos.getUmidade() : dadosMetereologicosDTO.umidade();
            String velDoVentoUpdate = dadosMetereologicosDTO.velDoVento() == null ? existingDadosMetereologicos.getVelDoVento() : dadosMetereologicosDTO.velDoVento();

            existingDadosMetereologicos.setCidade(cidadeUpdate);
            existingDadosMetereologicos.setData(dataUpdate);
            existingDadosMetereologicos.setTurno(turnoUpdate);
            existingDadosMetereologicos.setTempMin(tempMinUpdate);
            existingDadosMetereologicos.setTempMax(tempMaxUpdate);
            existingDadosMetereologicos.setClima(climaUpdate);
            existingDadosMetereologicos.setPrecipitacao(precipitacaoUpdate);
            existingDadosMetereologicos.setUmidade(umidadeUpdate);
            existingDadosMetereologicos.setVelDoVento(velDoVentoUpdate);

            DadosMetereologicos dadosMetereologicosSaved = dadosMetereologicosRepository.save(existingDadosMetereologicos);
            return entityMapper.toDadosMetereologicosDTO(dadosMetereologicosSaved);
    }

        return dadosMetereologicosDTO;
    }
}
