package srteixeiradias.libraryapi.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import srteixeiradias.libraryapi.domain.request.LivroCreateRequest;
import srteixeiradias.libraryapi.domain.response.LivroCreateResponse;

@RequestMapping("livro")
public interface LivroAPI {


    @PostMapping
    ResponseEntity<LivroCreateResponse> create (@RequestBody @Valid LivroCreateRequest request);
}
