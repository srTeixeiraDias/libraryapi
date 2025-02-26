package srteixeiradias.libraryapi.exception;

import java.util.List;

public class DomainException extends RuntimeException{

    protected final List<Error> errors;

    public DomainException(String message, List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
