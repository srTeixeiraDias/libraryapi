package srteixeiradias.libraryapi.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import srteixeiradias.libraryapi.domain.request.LivroCreateRequest;
import srteixeiradias.libraryapi.domain.request.LivroUpdateRequest;
import srteixeiradias.libraryapi.domain.response.LivroCreateResponse;
import srteixeiradias.libraryapi.domain.response.LivroGetResponse;

@RequestMapping("livro")
public interface LivroAPI {


    @PostMapping
    ResponseEntity<LivroCreateResponse> create(@RequestBody @Valid LivroCreateRequest request);

    @GetMapping("{id}")
    LivroGetResponse findById(@PathVariable(name = "id") String id);

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable String id);

    @PutMapping("{id}")
    LivroGetResponse update(@PathVariable(name = "id") String id, @RequestBody @Valid LivroUpdateRequest request);
}
