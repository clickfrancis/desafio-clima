package temp.desafio.api.infrastructure.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.core.exceptions.ValidacaoException;
import temp.desafio.api.core.usecase.CreateDadosMetereologicosUseCaseImp;
import temp.desafio.api.core.usecase.DeleteDadosMetereologicosUseCaseImp;
import temp.desafio.api.core.usecase.UpdateDadosMetereologicosUseCaseImp;
import temp.desafio.api.fixture.DadosMetereologicosDTOFixture;
import temp.desafio.api.infrastructure.mappers.DadosMetereologicosEntityMapper;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicos;
import temp.desafio.api.infrastructure.persistence.repositories.DadosMetereologicosRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DadosMetereologicosServiceTest {

    @InjectMocks
    DadosMetereologicosService dadosMetereologicosService;

    @Mock
    DadosMetereologicosRepository dadosMetereologicosRepository;

    @Mock
    DadosMetereologicosEntityMapper entityMapper;

    @Mock
    private CreateDadosMetereologicosUseCaseImp createDadosMetereologicos;

    @Mock
    private UpdateDadosMetereologicosUseCaseImp updateDadosMetereologicos;

    @Mock
    private DeleteDadosMetereologicosUseCaseImp deleteDadosMetereologicosUseCase;

    DadosMetereologicosDTO dadosMetereologicosCamposNull;

    DadosMetereologicosDTO dadosMetereoloicosExistente;

    DadosMetereologicosDTO dadosMetereologicosDTO;

    DadosMetereologicos dadosMetereologicos;

    @BeforeEach
    public void setUp() {

        this.dadosMetereologicosCamposNull = DadosMetereologicosDTOFixture.createDadosMetereologicosDTONull();
        this.dadosMetereoloicosExistente = DadosMetereologicosDTOFixture.createDadosMetereologicosDTOExistente();

        dadosMetereologicosDTO = new DadosMetereologicosDTO(
                "Salvador",
                LocalDate.now(),
                "0.0",
                "0.0",
                TipoTurno.MANHA,
                null,
                "0.0",
                "0.0",
                "0.0"
                );

        dadosMetereologicos = new DadosMetereologicos();
    }

    @Test
    @DisplayName("Esperado que cadastre os dados metereologicos com sucesso")
    void deveCadastrarDadosMetereologicosComSucesso() {

        when(entityMapper.toDadosMetereologicos(dadosMetereologicosDTO)).thenReturn(dadosMetereologicos);
        when(dadosMetereologicosRepository.save(dadosMetereologicos)).thenReturn(dadosMetereologicos);
        when(entityMapper.toDadosMetereologicosDTO(dadosMetereologicos)).thenReturn(dadosMetereologicosDTO);

        DadosMetereologicosDTO result = dadosMetereologicosService.createDadosMetereologicos(dadosMetereologicosDTO);

        assertNotNull(result);
        assertEquals(dadosMetereologicosDTO, result);

        verify(entityMapper).toDadosMetereologicos(dadosMetereologicosDTO);
        verify(dadosMetereologicosRepository).save(dadosMetereologicos);
        verify(entityMapper).toDadosMetereologicos(dadosMetereologicosDTO);
    }

    @Test
    @DisplayName("Esperado que não cadastre os dados metereologicos por campos vazios")
    public void naoDeveCadastrarDadosMetereologicosPorCamposVazios() {

        when(createDadosMetereologicos.execute(dadosMetereologicosCamposNull))
                .thenThrow(new ValidacaoException(""));

        assertThrows(ValidacaoException.class, () -> {
            dadosMetereologicosService.createDadosMetereologicos(dadosMetereologicosCamposNull);
        });
    }

    @Test
    @DisplayName("Esperado que aconteça erro por não permitir eventos iguais")
    public void naoDeveCadastrarDadosMetereologicosIguais() {

        when(entityMapper.toDadosMetereologicos(dadosMetereoloicosExistente)).thenReturn(dadosMetereologicos);

        when(createDadosMetereologicos.execute(dadosMetereoloicosExistente))
                .thenThrow(new ValidacaoException(""));

        assertThrows(ValidacaoException.class, () -> dadosMetereologicosService.createDadosMetereologicos(dadosMetereoloicosExistente));
    }

    @Test
    @DisplayName("Esperado que busque dados metereologicos por cidade")
    void deveBuscarPorCidade() {
        when(dadosMetereologicosRepository.findByCidade(dadosMetereologicosDTO.cidade()))
                .thenReturn(Optional.of(dadosMetereologicos));
        when(entityMapper.toDadosMetereologicosDTO(dadosMetereologicos)).thenReturn(dadosMetereologicosDTO);

        DadosMetereologicosDTO result = dadosMetereologicosService.findByCidade(dadosMetereologicosDTO.cidade());

        assertEquals(dadosMetereologicosDTO, result);

        verify(dadosMetereologicosRepository).findByCidade(dadosMetereologicosDTO.cidade());
        verify(entityMapper).toDadosMetereologicosDTO(dadosMetereologicos);
    }

    @Test
    @DisplayName("Esperado que liste dados metereologicos com sucesso")
    public void deveEncontrarTodosOsDadosMetereologicosComSucesso() {
        when(dadosMetereologicosRepository.findAll()).thenReturn(Collections.singletonList(dadosMetereologicos));
        when(entityMapper.toDadosMetereologicosDTO(dadosMetereologicos)).thenReturn(dadosMetereologicosDTO);

        List<DadosMetereologicosDTO> result = dadosMetereologicosService.getAllDadosMetereologicos();

        assertNotNull(result);

        verify(dadosMetereologicosRepository).findAll();
    }

    @Test
    @DisplayName("Esperado que atualize dados metereologicos com sucesso")
    public void deveAtualizarDadosMetereologicosComSucesso() {

        when(updateDadosMetereologicos.execute(dadosMetereologicosDTO)).thenReturn(dadosMetereologicosDTO);

        DadosMetereologicosDTO result = dadosMetereologicosService.updateDadosMeterologicos(dadosMetereologicosDTO);

        assertNotNull(result);
        assertEquals(dadosMetereologicosDTO, result);
    }

    @Test
    @DisplayName("Esperado que não atualize os dados metereologicos")
    public void naoDeveAtualizarDadosMetereologicosD() {
        when(updateDadosMetereologicos.execute(dadosMetereologicosCamposNull))
                .thenThrow(ValidacaoException.class);

        assertThrows(ValidacaoException.class, () -> dadosMetereologicosService.updateDadosMeterologicos(dadosMetereologicosCamposNull));
    }
}
