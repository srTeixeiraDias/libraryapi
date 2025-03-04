package srteixeiradias.libraryapi.domain.request;

import jakarta.validation.constraints.Min;
import srteixeiradias.libraryapi.domain.enuns.GeneroLivro;

public record LivroSearchRequest(
        String isbn,
        String titulo,
        String nomeAutor,
        GeneroLivro genero,
        Integer ano,

        @Min(0)
        Integer page,

        @Min(1)
        Integer size
) {}
