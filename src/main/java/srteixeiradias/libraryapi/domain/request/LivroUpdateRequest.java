package srteixeiradias.libraryapi.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;
import srteixeiradias.libraryapi.domain.enuns.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroUpdateRequest(
        @ISBN
        @NotBlank(message = "required field")
        @Size(min= 3, max = 20, message = "field outside standard size")
        String isbn,

        @NotBlank(message = "required field")
        @Size(min= 3, max = 100, message = "field outside standard size")
        String titulo,

        @NotNull(message = "required field")
        @Past(message = "date cannot be future")
        LocalDate dataPublicacao,

        @NotNull(message = "required field")
        GeneroLivro generoLivro,

        @NotNull(message = "required field")
        BigDecimal preco,

        @NotNull(message = "required field")
        UUID autorId
){

}
