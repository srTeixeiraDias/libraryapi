package srteixeiradias.libraryapi.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import srteixeiradias.libraryapi.api.LivroAPI;
import srteixeiradias.libraryapi.domain.request.LivroCreateRequest;
import srteixeiradias.libraryapi.domain.request.LivroUpdateRequest;
import srteixeiradias.libraryapi.domain.response.LivroCreateResponse;
import srteixeiradias.libraryapi.domain.response.LivroGetResponse;
import srteixeiradias.libraryapi.service.LivroService;

import java.util.UUID;

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

    @Override
    public LivroGetResponse findById(String id) {
        return livroService.findById(UUID.fromString(id));
    }

    @Override
    public void deleteById(String id) {
        livroService.deleteById(UUID.fromString(id));
    }

    @Override
    public ResponseEntity<LivroGetResponse> update(String id, LivroUpdateRequest request) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(livroService.update(UUID.fromString(id), request));
    }
}
