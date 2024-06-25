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
    public List<DadosMetereologicos> getAllDadosMetereologicos(){
        return dadosMetereologicosService.getAllDadosMetereologicos();
    }

    @PutMapping("/atualizar-dados-metereologicos/{cidade}/{data}/{turno}")
    public ResponseEntity<Void> updateDadosMetereologicos(
            @PathVariable String cidade,
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data,
            @PathVariable TipoTurno turno,
            @RequestBody DadosMetereologicos dadosMetereologicos) {
        dadosMetereologicosService.updateDadosMeterologicos(cidade, data, turno, dadosMetereologicos);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{cidade}/{data}/{turno}")
    public ResponseEntity<Void> delete(
            @PathVariable String cidade,
            @PathVariable @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate data,
            @PathVariable TipoTurno turno
    ) {
        dadosMetereologicosService.deleteDadosMeterologicos(cidade, data, turno);
        return ResponseEntity.noContent().build();
    }
}
