package srteixeiradias.libraryapi.api;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import srteixeiradias.libraryapi.domain.request.LivroCreateRequest;
import srteixeiradias.libraryapi.domain.request.LivroSearchRequest;
import srteixeiradias.libraryapi.domain.request.LivroUpdateRequest;
import srteixeiradias.libraryapi.domain.response.LivroCreateResponse;
import srteixeiradias.libraryapi.domain.response.LivroGetResponse;
import srteixeiradias.libraryapi.domain.response.LivroSearchResponse;

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

    @GetMapping
    ResponseEntity<Page<LivroSearchResponse>> buscarLivros(
            @RequestParam(name = "isbn", required = false) String isbn,
            @RequestParam(name = "titulo", required = false) String titulo,
            @RequestParam(name = "nomeAutor", required = false) String nomeAutor,
            @RequestParam(name = "genero", required = false) String genero,
            @RequestParam(name = "ano", required = false) Integer ano,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size);
}
