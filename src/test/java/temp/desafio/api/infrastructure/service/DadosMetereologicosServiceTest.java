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

    DadosMetereologicosDTO dadosMetereologicosDTO;

    DadosMetereologicos dadosMetereologicos;

    @BeforeEach
    public void setUp() {
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
    void deveCadastrarDadosMetereologicos() {

        when(entityMapper.toDadosMetereologicosEntity(dadosMetereologicosDTO)).thenReturn(dadosMetereologicos);
        when(dadosMetereologicosRepository.save(dadosMetereologicos)).thenReturn(dadosMetereologicos);
        when(entityMapper.toDadosMetereologicos(dadosMetereologicos)).thenReturn(dadosMetereologicosDTO);

        DadosMetereologicosDTO result = dadosMetereologicosService.createDadosMetereologicos(dadosMetereologicosDTO);

        assertNotNull(result);
        assertEquals(dadosMetereologicosDTO, result);

        verify(entityMapper).toDadosMetereologicosEntity(dadosMetereologicosDTO);
        verify(dadosMetereologicosRepository).save(dadosMetereologicos);
        verify(entityMapper).toDadosMetereologicos(dadosMetereologicos);
    }

    @Test
    @DisplayName("Esperado que busque dados metereologicos por cidade")
    void deveBuscarPorCidade() {
        when(dadosMetereologicosRepository.findByCidade(dadosMetereologicosDTO.cidade())).thenReturn(Optional.of(dadosMetereologicos));
        when(entityMapper.toDadosMetereologicos(dadosMetereologicos)).thenReturn(dadosMetereologicosDTO);

        DadosMetereologicosDTO result = dadosMetereologicosService.findByCidade(dadosMetereologicosDTO.cidade());

        assertNotNull(result);
        assertEquals(dadosMetereologicosDTO, result);

        verify(dadosMetereologicosRepository).findByCidade(dadosMetereologicosDTO.cidade());
        verify(entityMapper).toDadosMetereologicos(dadosMetereologicos);
    }

    @Test
    @DisplayName("Esperado que liste dados metereologicos com sucesso")
    public void deveEncontrarTodosOsDadosMetereologicosComSucesso() {
        when(dadosMetereologicosRepository.findAll()).thenReturn(Collections.singletonList(dadosMetereologicos));
        when(entityMapper.toDadosMetereologicos(dadosMetereologicos)).thenReturn(dadosMetereologicosDTO);

        List<DadosMetereologicosDTO> result = dadosMetereologicosService.getAllDadosMetereologicos();

        assertNotNull(result);

        verify(dadosMetereologicosRepository).findAll();
        verify(entityMapper).toDadosMetereologicos(dadosMetereologicos);
    }

    @Test
    @DisplayName("Esperado que atualize dados metereologicos com sucesso")
    public void deveAtualizarDadosMetereologicosComSucesso() {

        when(dadosMetereologicosRepository.findByCidadeAndDataAndTurno(
                dadosMetereologicosDTO.cidade(), dadosMetereologicosDTO.data(), dadosMetereologicosDTO.turno())
        ).thenReturn(Optional.of(dadosMetereologicos));
        when(dadosMetereologicosRepository.save(dadosMetereologicos)).thenReturn(dadosMetereologicos);
        when(entityMapper.toDadosMetereologicos(dadosMetereologicos)).thenReturn(dadosMetereologicosDTO);

        DadosMetereologicosDTO result = dadosMetereologicosService.updateDadosMeterologicos(
                dadosMetereologicosDTO.cidade(), dadosMetereologicosDTO.data(), dadosMetereologicosDTO.turno(), dadosMetereologicosDTO);

        assertNotNull(result);
        assertEquals(dadosMetereologicosDTO, result);

        verify(dadosMetereologicosRepository).findByCidadeAndDataAndTurno(
                dadosMetereologicosDTO.cidade(),
                dadosMetereologicosDTO.data(),
                dadosMetereologicosDTO.turno());
        verify(dadosMetereologicosRepository).save(dadosMetereologicos);
        verify(entityMapper).toDadosMetereologicos(dadosMetereologicos);
        verifyNoMoreInteractions(dadosMetereologicosRepository);
    }

    @Test
    @DisplayName("Esperado que não atualize os dados metereologicos")
    public void naoDeveAtualizarDadosMetereologicos() {
        when(dadosMetereologicosRepository.findByCidadeAndDataAndTurno(
                dadosMetereologicosDTO.cidade(),
                dadosMetereologicosDTO.data(),
                null)
        ).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            dadosMetereologicosService.updateDadosMeterologicos(
                    dadosMetereologicosDTO.cidade(), dadosMetereologicosDTO.data(), null, dadosMetereologicosDTO);
        });

        assertEquals("DadosMetereologicos not found for cidade: " + dadosMetereologicosDTO.cidade(), exception.getMessage());

        verify(dadosMetereologicosRepository).findByCidadeAndDataAndTurno(
                dadosMetereologicosDTO.cidade(),
                dadosMetereologicosDTO.data(),
                dadosMetereologicosDTO.turno());
        verify(dadosMetereologicosRepository, never()).save(any(DadosMetereologicos.class));
        verify(entityMapper, never()).toDadosMetereologicos(any(DadosMetereologicos.class));
    }
}
