package srteixeiradias.libraryapi.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import srteixeiradias.libraryapi.domain.request.AutorCreateRequest;
import srteixeiradias.libraryapi.domain.response.AutorCreateResponse;


@RequestMapping(value = "autores")
public interface AutorAPI {

    @PostMapping
    ResponseEntity<AutorCreateResponse> create(@RequestBody AutorCreateRequest request);

}
