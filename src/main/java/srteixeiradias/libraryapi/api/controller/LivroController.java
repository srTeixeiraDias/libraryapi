package srteixeiradias.libraryapi.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import srteixeiradias.libraryapi.api.LivroAPI;
import srteixeiradias.libraryapi.domain.request.LivroCreateRequest;
import srteixeiradias.libraryapi.domain.response.LivroCreateResponse;
import srteixeiradias.libraryapi.service.LivroService;

@RestController
public class LivroController implements LivroAPI {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }


    @Override
    public ResponseEntity<LivroCreateResponse> create(LivroCreateRequest request) {
       return ResponseEntity.status(HttpStatus.CREATED).body(livroService.save(request));
    }
}
