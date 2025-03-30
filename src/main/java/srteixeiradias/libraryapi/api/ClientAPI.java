package srteixeiradias.libraryapi.api;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import srteixeiradias.libraryapi.domain.request.ClientCreateRequest;
import srteixeiradias.libraryapi.domain.response.ClientCreateResponse;
import srteixeiradias.libraryapi.domain.response.ClientGetResponse;

@RequestMapping(value = "clients")
public interface ClientAPI {


    @PostMapping
    ResponseEntity<ClientCreateResponse> create (@RequestBody @Valid ClientCreateRequest request);

    @GetMapping("{clientId}")
    ClientGetResponse findByClientId(@PathVariable(name="clientId") String clientId);
}
