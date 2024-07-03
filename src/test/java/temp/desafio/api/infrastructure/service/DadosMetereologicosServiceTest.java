package temp.desafio.api.infrastructure.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.infrastructure.mappers.DadosMetereologicosEntityMapper;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicosEntity;
import temp.desafio.api.infrastructure.persistence.repositories.DadosMetereologicosRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    DadosMetereologicos dadosMetereologicos;

    DadosMetereologicosEntity dadosMetereologicosEntity;

    @BeforeEach
    public void setUp() {
        dadosMetereologicos = new DadosMetereologicos(
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

        dadosMetereologicosEntity = new DadosMetereologicosEntity();
    }

    @Test
    void deveCadastrarDadosMetereologicos() {

        when(entityMapper.toDadosMetereologicosEntity(dadosMetereologicos)).thenReturn(dadosMetereologicosEntity);
        when(dadosMetereologicosRepository.save(dadosMetereologicosEntity)).thenReturn(dadosMetereologicosEntity);
        when(entityMapper.toDadosMetereologicos(dadosMetereologicosEntity)).thenReturn(dadosMetereologicos);

        DadosMetereologicos result = dadosMetereologicosService.createDadosMetereologicos(dadosMetereologicos);

        assertNotNull(result);
        assertEquals(dadosMetereologicos, result);

        verify(entityMapper).toDadosMetereologicosEntity(dadosMetereologicos);
        verify(dadosMetereologicosRepository).save(dadosMetereologicosEntity);
        verify(entityMapper).toDadosMetereologicos(dadosMetereologicosEntity);
    }

    @Test
    void deveBuscarPorCidade() {
        when(dadosMetereologicosRepository.findByCidade(dadosMetereologicos.cidade())).thenReturn(Optional.of(dadosMetereologicosEntity));
        when(entityMapper.toDadosMetereologicos(dadosMetereologicosEntity)).thenReturn(dadosMetereologicos);

        DadosMetereologicos result = dadosMetereologicosService.findByCidade(dadosMetereologicos.cidade());

        assertNotNull(result);
        assertEquals(dadosMetereologicos, result);

        verify(dadosMetereologicosRepository).findByCidade(dadosMetereologicos.cidade());
        verify(entityMapper).toDadosMetereologicos(dadosMetereologicosEntity);
    }

    @Test
    public void deveEncontrarTodosOsDadosMetereologicosComSucesso() {
        when(dadosMetereologicosRepository.findAll()).thenReturn(Collections.singletonList(dadosMetereologicosEntity));
        when(entityMapper.toDadosMetereologicos(dadosMetereologicosEntity)).thenReturn(dadosMetereologicos);

        List<DadosMetereologicos> result = dadosMetereologicosService.getAllDadosMetereologicos();

        assertNotNull(result);

        verify(dadosMetereologicosRepository).findAll();
        verify(entityMapper).toDadosMetereologicos(dadosMetereologicosEntity);
    }

    @Test
    public void deveAtualizarDadosMetereologicosComSucesso() {

        when(dadosMetereologicosRepository.findByCidadeAndDataAndTurno(
                dadosMetereologicos.cidade(), dadosMetereologicos.data(), dadosMetereologicos.turno())
        ).thenReturn(Optional.of(dadosMetereologicosEntity));
        when(dadosMetereologicosRepository.save(dadosMetereologicosEntity)).thenReturn(dadosMetereologicosEntity);
        when(entityMapper.toDadosMetereologicos(dadosMetereologicosEntity)).thenReturn(dadosMetereologicos);

        DadosMetereologicos result = dadosMetereologicosService.updateDadosMeterologicos(
                dadosMetereologicos.cidade(), dadosMetereologicos.data(), dadosMetereologicos.turno(), dadosMetereologicos);

        assertNotNull(result);
        assertEquals(dadosMetereologicos, result);

        verify(dadosMetereologicosRepository).findByCidadeAndDataAndTurno(
                dadosMetereologicos.cidade(),
                dadosMetereologicos.data(),
                dadosMetereologicos.turno());
        verify(dadosMetereologicosRepository).save(dadosMetereologicosEntity);
        verify(entityMapper).toDadosMetereologicos(dadosMetereologicosEntity);
        verifyNoMoreInteractions(dadosMetereologicosRepository);
    }

    @Test
    public void naoDeveAtualizarDadosMetereologicos() {
        when(dadosMetereologicosRepository.findByCidadeAndDataAndTurno(
                dadosMetereologicos.cidade(),
                dadosMetereologicos.data(),
                null)
        ).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            dadosMetereologicosService.updateDadosMeterologicos(
                    dadosMetereologicos.cidade(), dadosMetereologicos.data(), null, dadosMetereologicos);
        });

        assertEquals("DadosMetereologicos not found for cidade: " + dadosMetereologicos.cidade(), exception.getMessage());

        verify(dadosMetereologicosRepository).findByCidadeAndDataAndTurno(
                dadosMetereologicos.cidade(),
                dadosMetereologicos.data(),
                dadosMetereologicos.turno());
        verify(dadosMetereologicosRepository, never()).save(any(DadosMetereologicosEntity.class));
        verify(entityMapper, never()).toDadosMetereologicos(any(DadosMetereologicosEntity.class));
    }
}
