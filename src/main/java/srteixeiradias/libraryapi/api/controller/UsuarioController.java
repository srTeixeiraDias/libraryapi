package srteixeiradias.libraryapi.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import srteixeiradias.libraryapi.api.UsuarioAPI;
import srteixeiradias.libraryapi.domain.request.UsuarioCreateRequest;
import srteixeiradias.libraryapi.domain.response.UsuarioCreateResponse;
import srteixeiradias.libraryapi.service.UsuarioService;

@RestController
public class UsuarioController implements UsuarioAPI {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public ResponseEntity<UsuarioCreateResponse> create(UsuarioCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.create(request));
    }
}
