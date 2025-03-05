package srteixeiradias.libraryapi.secutiry;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import srteixeiradias.libraryapi.domain.model.Usuario;
import srteixeiradias.libraryapi.service.UsuarioService;

public class CustomUserDatailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    public CustomUserDatailsService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByLogin(login).toEntity();

        return User.builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(usuario.getRoles().toArray(new String[usuario.getRoles().size()]))
                .build();
    }
}
