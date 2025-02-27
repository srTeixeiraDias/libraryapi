package srteixeiradias.libraryapi.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import srteixeiradias.libraryapi.domain.request.AutorCreateRequest;
import srteixeiradias.libraryapi.domain.request.AutorUpdateRequest;
import srteixeiradias.libraryapi.domain.response.AutorCreateResponse;
import srteixeiradias.libraryapi.domain.response.AutorGetResponse;
import srteixeiradias.libraryapi.domain.response.AutorListResponse;

import java.util.List;


@RequestMapping(value = "autores")
public interface AutorAPI {

    @PostMapping
    ResponseEntity<AutorCreateResponse> create(@RequestBody @Valid AutorCreateRequest request);

    @GetMapping("{id}")
    AutorGetResponse findById(@PathVariable(name = "id") String id);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable(name = "id") String id);

    @GetMapping
    List<AutorListResponse> list(@RequestParam(value = "nome", required = false) String nome,
                                  @RequestParam(value = "nacionalidade", required = false) String nacionalidade);

    @PutMapping("{id}")
    AutorGetResponse update(@PathVariable(name = "id") String id, @RequestBody @Valid AutorUpdateRequest request);

}
