package srteixeiradias.libraryapi.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import srteixeiradias.libraryapi.domain.request.UsuarioCreateRequest;
import srteixeiradias.libraryapi.domain.response.UsuarioCreateResponse;
import srteixeiradias.libraryapi.domain.response.UsuarioGetResponse;
import srteixeiradias.libraryapi.exception.NotFoundException;
import srteixeiradias.libraryapi.repository.UsuarioRepository;
import srteixeiradias.libraryapi.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    @Override
    public UsuarioCreateResponse create(UsuarioCreateRequest request) {
        final var user = request.toEntity();
        user.setSenha(encoder.encode(request.senha()));
        return UsuarioCreateResponse.fromEntity(usuarioRepository.save(user));
    }

    @Override
    public UsuarioGetResponse findByLogin(String login) {
        return usuarioRepository.findByLogin(login)
                .map(UsuarioGetResponse::fromEntity)
                .orElseThrow(() -> new NotFoundException("Usuario com Login: " + login + "não existe"));
    }
}
