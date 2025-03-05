package srteixeiradias.libraryapi.domain.response;

import srteixeiradias.libraryapi.domain.model.Autor;
import srteixeiradias.libraryapi.domain.model.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record AutorGetResponse(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UsuarioResponse usuario
) {
    public static AutorGetResponse fromEntity(Autor autor) {
        return new AutorGetResponse(
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