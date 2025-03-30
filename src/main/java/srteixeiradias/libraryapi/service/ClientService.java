package srteixeiradias.libraryapi.service;

import srteixeiradias.libraryapi.domain.request.ClientCreateRequest;
import srteixeiradias.libraryapi.domain.response.ClientCreateResponse;
import srteixeiradias.libraryapi.domain.response.ClientGetResponse;

public interface ClientService {

    ClientCreateResponse create(final ClientCreateRequest request);

    ClientGetResponse findByClientId(final String clientId);
}
