package srteixeiradias.libraryapi.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import srteixeiradias.libraryapi.exception.DomainException;
import srteixeiradias.libraryapi.exception.Error;
import srteixeiradias.libraryapi.exception.NotFoundException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String GENERIC_ERROR_MESSAGE = "An error has ocurred";

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(final NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
    }

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<?> handleDomainException(final DomainException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiError.from(ex));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleArgumentNotValidException(final MethodArgumentNotValidException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiError.from(ex));
    }


    record ApiError(String message, List<Error> errors) {

        static ApiError from(DomainException ex){
            return new ApiError(ex.getMessage(), ex.getErrors());
        }

        static ApiError from(MethodArgumentNotValidException ex){
            List<Error> errors = ex.getBindingResult().getFieldErrors()           //cria lista com os erros de validação
                    .stream()
                    .map(fieldError ->
                            //Cria um novo objeto Error contendo o nome do campo e a mensagem de erro correspondente
                            new Error(fieldError.getField() + ": " + fieldError.getDefaultMessage()))
                    .toList();
            return new ApiError("Validation error", errors);
        }

        static ApiError from(NotFoundException ex) {
            log.error(ex.getMessage(), ex);
            return new ApiError(GENERIC_ERROR_MESSAGE, null);
        }
    }

}
