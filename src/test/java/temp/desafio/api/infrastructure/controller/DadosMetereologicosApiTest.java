package temp.desafio.api.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.enums.TipoClima;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.core.exceptions.ValidacaoException;
import temp.desafio.api.infrastructure.persistence.entity.DadosMetereologicosEntity;
import temp.desafio.api.infrastructure.persistence.repositories.DadosMetereologicosRepository;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class DadosMetereologicosApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    JacksonTester<DadosMetereologicos> dadosMetereologicosJson;

    @Autowired
    DadosMetereologicosRepository dadosMetereologicosRepository;

    @MockBean
    DadosMetereologicos dadosMetereologicos;

    @BeforeEach
    public void setUp() {
        DadosMetereologicosEntity dadosMetereolicosEntity = new DadosMetereologicosEntity();
        dadosMetereolicosEntity.setCidade("Salvador");
        dadosMetereolicosEntity.setData(LocalDate.of(2024, 7,2));
        dadosMetereolicosEntity.setTempMin("");
        dadosMetereolicosEntity.setTempMax("");
        dadosMetereolicosEntity.setTurno(TipoTurno.MANHA);
        dadosMetereolicosEntity.setClima(TipoClima.ENSOLARADO);
        dadosMetereolicosEntity.setUmidade("");
        dadosMetereolicosEntity.setPrecipitacao("");
        dadosMetereolicosEntity.setVelDoVento("2.0");
        dadosMetereologicosRepository.save(dadosMetereolicosEntity);
    }

    @AfterEach
    public void setDown() {
        dadosMetereologicosRepository.deleteAll();
    }

    @Test
    @DisplayName("Deveria devolver código 200 quando solicitar a requisição para criar")
    void criarEventoClimatico() throws Exception {

        String dadosMetereologicosRequest = mapper.writeValueAsString(
                new DadosMetereologicos(
                        "Salvador",
                        LocalDate.of(2024,7,3),
                        "28°",
                        "32°",
                        TipoTurno.MANHA,
                        TipoClima.ENSOLARADO,
                        null,
                        null,
                        null
                )
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/dados-metereologicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(dadosMetereologicosRequest))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Deveria devolver código 422 quando solicitar a requisição para criar")
    void negarCriacaoEventoClimatico() throws Exception {

        String dadosMetereologicosRequest = mapper.writeValueAsString(
                new DadosMetereologicos(
                        "Salvador",
                        LocalDate.of(2024,7,2),
                        "28°",
                        "32°",
                        TipoTurno.MANHA,
                        TipoClima.ENSOLARADO,
                        null,
                        null,
                        null
                )
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/dados-metereologicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(dadosMetereologicosRequest))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(result -> Assertions.assertInstanceOf(ValidacaoException.class, result.getResolvedException()))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Deveria devolver status code 200 quando solicitar requisição para listar")
    public void listarEventosClimaticos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/dados-metereologicos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Deveria devolver status code 200 quando solicitar requisição de atualizacão")
    public void atualizarEventosClimaticos() throws Exception {

        String dadosMetereologicosRequest = mapper.writeValueAsString(
                new DadosMetereologicos(
                        "Salvador",
                        LocalDate.of(2024,7,2),
                        "28°",
                        "32°",
                        TipoTurno.MANHA,
                        TipoClima.ENSOLARADO,
                        null,
                        null,
                        null
                )
        );

        mockMvc.perform(MockMvcRequestBuilders.put(
                "/dados-metereologicos/atualizar-dados-metereologicos/{cidade}/{data}/{turno}", "Salvador", "2-7-2024","MANHA")
                .content(dadosMetereologicosRequest)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cidade").value("Salvador"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("02-07-2024"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.turno").value("MANHA"))
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    @DisplayName("Deveria devolver status 202 quando solicitar requisição de deletar")
    public void deleteEmployeeAPI() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders.delete("/dados-metereologicos/{cidade}/{data}/{turno}", "Salvador", "2-7-2024","MANHA") )
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}
