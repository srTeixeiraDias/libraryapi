package srteixeiradias.libraryapi.domain.response;

import srteixeiradias.libraryapi.domain.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

public record AutorListResponse(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade,
        UsuarioResponse usuario
) {
    public static AutorListResponse fromEntity(Autor autor) {
        return new AutorListResponse(
                autor.getId(),
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade(),
                UsuarioResponse.fromEntity(autor.getUser())
        );
    }
}