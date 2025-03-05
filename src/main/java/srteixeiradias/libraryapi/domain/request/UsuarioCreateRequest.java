package srteixeiradias.libraryapi.domain.request;

import srteixeiradias.libraryapi.domain.model.Usuario;

import java.util.List;

public record UsuarioCreateRequest(

        String login,

        String senha,

        List<String> roles

) {

    public Usuario toEntity(){
        Usuario user = new Usuario();
        user.setLogin(this.login);
        user.setSenha(this.senha);
        user.setRoles(this.roles);
        return user;
    }
}
