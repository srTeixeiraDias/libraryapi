package srteixeiradias.libraryapi.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import srteixeiradias.libraryapi.api.AutorAPI;
import srteixeiradias.libraryapi.domain.request.AutorCreateRequest;
import srteixeiradias.libraryapi.domain.response.AutorCreateResponse;
import srteixeiradias.libraryapi.service.AutorService;

@RestController
public class AutorController implements AutorAPI {

    private final AutorService autorService;

    public AutorController(AutorService autorService){

        this.autorService = autorService;
    } 


    @Override
    public ResponseEntity<AutorCreateResponse> create(final AutorCreateRequest request) {

        AutorCreateResponse response = autorService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
