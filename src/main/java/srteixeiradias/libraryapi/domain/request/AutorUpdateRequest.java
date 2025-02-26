package srteixeiradias.libraryapi.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AutorUpdateRequest(
        @NotBlank(message = "required field")
        @Size(min= 3, max = 100, message = "field outside standard size")
        String nome,

        @NotNull(message = "required field")
        @Past(message = "date cannot be future")
        LocalDate dataNascimento,

        @NotBlank(message = "required field")
        @Size(min= 3, max = 50, message = "field outside standard size")
        String nacionalidade
) {}