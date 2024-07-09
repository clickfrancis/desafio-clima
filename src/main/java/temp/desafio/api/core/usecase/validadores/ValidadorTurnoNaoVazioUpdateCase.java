package temp.desafio.api.core.usecase.validadores;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.exceptions.ValidacaoException;
import temp.desafio.api.infrastructure.persistence.repositories.DadosMetereologicosRepository;

@Component
@RequiredArgsConstructor
public class ValidadorTurnoNaoVazioUpdateCase {

    @Autowired
    private final DadosMetereologicosRepository dadosMetereologicosRepository;

    public void validar(DadosMetereologicosDTO dadosMetereologicosDTO) {

        boolean localizador = dadosMetereologicosRepository.findClimaAtivoByCidadeAndDataAndTuno(
                dadosMetereologicosDTO.cidade(),
                dadosMetereologicosDTO.data(),
                dadosMetereologicosDTO.turno());
        if (!localizador) {
            throw new ValidacaoException("Não existe evento metereológico nessa data e turno para ser atualizado.");
        }
    }
}
