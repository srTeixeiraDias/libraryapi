package srteixeiradias.libraryapi.domain.response;

import srteixeiradias.libraryapi.domain.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UsuarioCreateResponse(
        UUID id,
        String login,
        String senha,
        String email,
        List<String> roles,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static UsuarioCreateResponse fromEntity(Usuario user){
        return new UsuarioCreateResponse(
                user.getId(),
                user.getLogin(),
                user.getSenha(),
                user.getEmail(),
                user.getRoles(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
