package srteixeiradias.libraryapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import srteixeiradias.libraryapi.domain.request.UsuarioCreateRequest;
import srteixeiradias.libraryapi.domain.response.UsuarioCreateResponse;

@RequestMapping(value = "usuarios")
public interface UsuarioAPI {

    @PostMapping
    ResponseEntity<UsuarioCreateResponse> create (@RequestBody UsuarioCreateRequest request);



}
