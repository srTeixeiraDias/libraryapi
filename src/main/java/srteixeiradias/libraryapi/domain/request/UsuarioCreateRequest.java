package srteixeiradias.libraryapi.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import srteixeiradias.libraryapi.domain.model.Usuario;

import java.util.List;

public record UsuarioCreateRequest(

        @NotBlank(message = "required field")
        String login,

        @NotBlank(message = "required field")
        String senha,

        @Email (message = "Email inválido")
        @NotBlank(message = "required field")
        String email,

        List<String> roles

) {

    public Usuario toEntity(){
        Usuario user = new Usuario();
        user.setLogin(this.login);
        user.setSenha(this.senha);
        user.setEmail(this.email);
        user.setRoles(this.roles);
        return user;
    }
}
