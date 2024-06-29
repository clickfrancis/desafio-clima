package temp.desafio.api.infrastructure.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import temp.desafio.api.core.exceptions.ValidacaoException;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException err) {
        return ResponseEntity.badRequest().body(err.getMessage());
    }
}