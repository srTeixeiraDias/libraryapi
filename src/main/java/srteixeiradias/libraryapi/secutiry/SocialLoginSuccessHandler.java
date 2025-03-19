package srteixeiradias.libraryapi.secutiry;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import srteixeiradias.libraryapi.domain.model.Usuario;
import srteixeiradias.libraryapi.service.UsuarioService;

import java.io.IOException;

@Component
public class SocialLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UsuarioService usuarioService;

    public SocialLoginSuccessHandler(final UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String email = auth2AuthenticationToken.getPrincipal().getAttribute("email");

        Usuario user = usuarioService.findByEmail(email).toEntity();

        authentication = new CustomAuthentication(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
