package temp.desafio.api.infrastructure.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.enums.TipoTurno;
import temp.desafio.api.infrastructure.service.DadosMetereologicosService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping({"/dados-metereologicos"})
@AllArgsConstructor
public class DadosMetereologicosApi {

    @Autowired
    private final DadosMetereologicosService dadosMetereologicosService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosMetereologicos> createDadosMetereologicos(@RequestBody DadosMetereologicos dadosMetereologicos) {
        DadosMetereologicos createdDadosMetereologicos = dadosMetereologicosService.createDadosMetereologicos(dadosMetereologicos);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDadosMetereologicos);
    }

    @GetMapping
    public ResponseEntity<List<DadosMetereologicos>> getAllDadosMetereologicos(){
        List<DadosMetereologicos> dadosMetereologicos = dadosMetereologicosService.getAllDadosMetereologicos();
        return ResponseEntity.status(HttpStatus.OK).body(dadosMetereologicos);
    }

    @PutMapping("/atualizar-dados-metereologicos/{cidade}/{data}/{turno}")
    public ResponseEntity<DadosMetereologicos> updateDadosMetereologicos(
            @PathVariable String cidade,
            @PathVariable @DateTimeFormat(pattern = "d-M-yyyy") String data,
            @PathVariable TipoTurno turno,
            @RequestBody DadosMetereologicos dadosMetereologicos) {
        LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("d-M-yyyy"));
        dadosMetereologicosService.updateDadosMeterologicos(cidade, localDate, turno, dadosMetereologicos);
        return ResponseEntity.status(HttpStatus.OK).body(dadosMetereologicos);
    }

    @DeleteMapping(path = "/{cidade}/{data}/{turno}")
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
