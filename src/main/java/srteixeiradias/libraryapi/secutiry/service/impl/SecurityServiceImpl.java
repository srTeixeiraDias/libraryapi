package srteixeiradias.libraryapi.secutiry.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import srteixeiradias.libraryapi.domain.model.Usuario;
import srteixeiradias.libraryapi.secutiry.CustomAuthentication;
import srteixeiradias.libraryapi.secutiry.service.SecurityService;
import srteixeiradias.libraryapi.service.UsuarioService;

@Component
public class SecurityServiceImpl implements SecurityService {

    private final UsuarioService usuarioService;

    public SecurityServiceImpl(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuario findUsuarioAutenticado(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication instanceof CustomAuthentication customAuthentication)
            return customAuthentication.getUsuario();

        return null;
    }
}
