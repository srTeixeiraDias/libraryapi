package srteixeiradias.libraryapi.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import srteixeiradias.libraryapi.api.AutorAPI;
import srteixeiradias.libraryapi.domain.request.AutorCreateRequest;
import srteixeiradias.libraryapi.domain.request.AutorUpdateRequest;
import srteixeiradias.libraryapi.domain.response.AutorCreateResponse;
import srteixeiradias.libraryapi.domain.response.AutorGetResponse;
import srteixeiradias.libraryapi.domain.response.AutorListResponse;
import srteixeiradias.libraryapi.service.AutorService;

import java.util.List;
import java.util.UUID;

@RestController
public class AutorController implements AutorAPI {

    private final AutorService autorService;

    public AutorController(AutorService autorService){
        this.autorService = autorService;
    } 

    @Override
    public ResponseEntity<AutorCreateResponse> create(final AutorCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(autorService.create(request));
    }

    @Override
    public AutorGetResponse findById(String id) {
        return autorService.findById(UUID.fromString(id));
    }

    @Override
    public void deleteById(String id) {
       autorService.deleteById(UUID.fromString(id));
    }

    @Override
    public List<AutorListResponse> list(String nome, String nacionalidade) {
        return autorService.listAutores(nome, nacionalidade);
    }

    @Override
    public AutorGetResponse update(String id, AutorUpdateRequest request) {
        return autorService.update(id, request);
    }
}
