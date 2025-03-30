package srteixeiradias.libraryapi.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import srteixeiradias.libraryapi.api.ClientAPI;
import srteixeiradias.libraryapi.domain.request.ClientCreateRequest;
import srteixeiradias.libraryapi.domain.response.ClientCreateResponse;
import srteixeiradias.libraryapi.domain.response.ClientGetResponse;
import srteixeiradias.libraryapi.service.ClientService;

@RestController
public class ClientController implements ClientAPI {

    private final ClientService clientService;

    public ClientController(final ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ResponseEntity<ClientCreateResponse> create(ClientCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(request));
    }

    @Override
    public ClientGetResponse findByClientId(String clientId) {
        return clientService.findByClientId(clientId);
    }
}
