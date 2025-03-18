package srteixeiradias.libraryapi.domain.response;

import srteixeiradias.libraryapi.domain.model.Usuario;

import java.util.UUID;

public record UsuarioResponse(
        String login,
        UUID id,
        String email
) {

    public static UsuarioResponse fromEntity(Usuario user){
        return new UsuarioResponse(
                user.getLogin(),
                user.getId(),
                user.getEmail()
        );
    }
}
