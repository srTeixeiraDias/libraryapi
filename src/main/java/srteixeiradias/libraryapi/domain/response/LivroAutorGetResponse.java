package srteixeiradias.libraryapi.domain.response;

import srteixeiradias.libraryapi.domain.model.Autor;

import java.time.LocalDate;

public record LivroAutorGetResponse(
        String nome,
        LocalDate dataNascimento,
        String nacionalidade
) {
    public static LivroAutorGetResponse from(Autor autor) {
        return new LivroAutorGetResponse(
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade()
        );
    }
}