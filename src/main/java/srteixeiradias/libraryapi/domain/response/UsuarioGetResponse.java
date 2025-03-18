package srteixeiradias.libraryapi.domain.response;

import srteixeiradias.libraryapi.domain.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UsuarioGetResponse(

        UUID id,
        String login,
        String senha,
        String email,
        List<String> roles,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){

    public static UsuarioGetResponse fromEntity(Usuario user){
        return new UsuarioGetResponse(
                user.getId(),
                user.getLogin(),
                user.getSenha(),
                user.getEmail(),
                user.getRoles(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public Usuario toEntity(){
        Usuario user = new Usuario();

        user.setId(this.id);
        user.setLogin(this.login);
        user.setSenha(this.senha);
        user.setEmail(this.email);
        user.setRoles(this.roles);
        user.setCreatedAt(this.createdAt);
        user.setUpdatedAt(this.updatedAt);

        return user;

    }
}
