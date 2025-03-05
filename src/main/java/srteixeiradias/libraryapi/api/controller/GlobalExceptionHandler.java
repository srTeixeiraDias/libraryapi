package srteixeiradias.libraryapi.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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

    private static final String GENERIC_ERROR_MESSAGE = "Ocorreu um erro";

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

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiError.from(ex));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> handleAccesDeniedException(AccessDeniedException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiError.from(ex));
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
            return new ApiError("Erro na validação de campos", errors);
        }

        static ApiError from(IllegalArgumentException ex){
            List<Error> errors = List.of(new Error("Parametro invalido: " + ex.getMessage()));
            return new ApiError(GENERIC_ERROR_MESSAGE, errors);
        }

        static ApiError from(NotFoundException ex) {
            log.error(ex.getMessage(), ex);
            List<Error> errors = List.of(new Error(ex.getMessage()));
            return new ApiError(GENERIC_ERROR_MESSAGE, errors);
        }

        static ApiError from(AccessDeniedException ex) {
            log.error(ex.getMessage(), ex);
            List<Error> errors = List.of(new Error(ex.getMessage()));
            return new ApiError(GENERIC_ERROR_MESSAGE, errors);
        }

        static ApiError from(RuntimeException ex) {
            log.error(ex.getMessage(), ex);
            List<Error> errors = List.of(new Error(ex.getMessage()));
            return new ApiError(GENERIC_ERROR_MESSAGE, errors);
        }
    }

}
