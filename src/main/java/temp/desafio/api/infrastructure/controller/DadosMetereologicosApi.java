package temp.desafio.api.infrastructure.controller;

import jakarta.transaction.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicosDTO;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.infrastructure.service.DadosMetereologicosService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping({"/dados-metereologicos"})
public class DadosMetereologicosApi {

    private final DadosMetereologicosService dadosMetereologicosService = new DadosMetereologicosService();

    @PostMapping
    @Transactional
    public ResponseEntity<DadosMetereologicosDTO> createDadosMetereologicos(@RequestBody DadosMetereologicosDTO dadosMetereologicosDTO) {
        DadosMetereologicosDTO createdDadosMetereologicos = dadosMetereologicosService.createDadosMetereologicos(dadosMetereologicosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDadosMetereologicos);
    }

    @GetMapping
    public ResponseEntity<List<DadosMetereologicosDTO>> getAllDadosMetereologicos(){
        List<DadosMetereologicosDTO> dadosMetereologicoDTOS = dadosMetereologicosService.getAllDadosMetereologicos();
        return ResponseEntity.status(HttpStatus.OK).body(dadosMetereologicoDTOS);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosMetereologicosDTO> updateDadosMetereologicos(
            @PathVariable String cidade,
            @PathVariable @DateTimeFormat(pattern = "d-M-yyyy") String data,
            @PathVariable TipoTurno turno,
            @RequestBody DadosMetereologicosDTO dadosMetereologicosDTO) {
        LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("d-M-yyyy"));
        dadosMetereologicosService.updateDadosMeterologicos(cidade, localDate, turno, dadosMetereologicosDTO);
        return ResponseEntity.status(HttpStatus.OK).body(dadosMetereologicosDTO);
    }

    @DeleteMapping(path = "/{cidade}/{data}/{turno}")
    @Transactional
    public ResponseEntity<HttpStatus> delete(
            @PathVariable String cidade,
            @PathVariable @DateTimeFormat(pattern = "d-M-yyyy") String data,
            @PathVariable TipoTurno turno
    ) {
        LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("d-M-yyyy"));
        dadosMetereologicosService.deleteDadosMeterologicos(cidade, localDate , turno);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
