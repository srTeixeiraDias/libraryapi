package srteixeiradias.libraryapi.domain.response;

import srteixeiradias.libraryapi.domain.model.Autor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record AutorCreateResponse(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UsuarioResponse usuario
) {
    public static AutorCreateResponse fromEntity(Autor autor) {
        return new AutorCreateResponse(
                autor.getId(),
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade(),
                autor.getCreatedAt(),
                autor.getUpdatedAt(),
                UsuarioResponse.fromEntity(autor.getUser())
        );
    }
}