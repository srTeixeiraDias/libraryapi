package srteixeiradias.libraryapi.secutiry;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import srteixeiradias.libraryapi.domain.model.Usuario;
import srteixeiradias.libraryapi.exception.NotFoundException;
import srteixeiradias.libraryapi.service.UsuarioService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(final UsuarioService usuarioService,
                                        final PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Usuario user = usuarioService.findByLogin(authentication.getName()).toEntity();

        if(passwordEncoder.matches(authentication.getCredentials().toString(), user.getSenha()))
            return new CustomAuthentication(user);

        throw new NotFoundException("Usuario não enconcontrado");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
