package temp.desafio.api.infrastructure.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import temp.desafio.api.core.dadosMetereologico.dto.DadosMetereologicos;
import temp.desafio.api.core.usecase.CreateDadosMetereologicosUseCase;
import temp.desafio.api.infrastructure.dto.DadosMetereologicosDto;

@RestController
@RequestMapping("/dadosMetereologicos")
@AllArgsConstructor
public class DadosMetereologicosController {

    private final CreateDadosMetereologicosUseCase createDadosMetereologicosUseCase;
    private final DadosMetereologicosDtoMapper dadosMetereologicosDtoMapper;

    @PostMapping
    public ResponseEntity<DadosMetereologicosDto> createDadosMetereologicos(@RequestBody DadosMetereologicosDto dadosMetereologicosDto) {
        DadosMetereologicos novoDadosMetereologicos = createDadosMetereologicosUseCase.execute(dadosMetereologicosDtoMapper.toDomain(dadosMetereologicosDto));
        DadosMetereologicosDto novoDadosMetereologicosDto = dadosMetereologicosDtoMapper.toDto(novoDadosMetereologicos);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDadosMetereologicosDto);
    }
}
