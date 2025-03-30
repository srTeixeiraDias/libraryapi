package srteixeiradias.libraryapi.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import srteixeiradias.libraryapi.domain.request.ClientCreateRequest;
import srteixeiradias.libraryapi.domain.response.ClientCreateResponse;
import srteixeiradias.libraryapi.domain.response.ClientGetResponse;
import srteixeiradias.libraryapi.exception.NotFoundException;
import srteixeiradias.libraryapi.repository.ClientRepository;
import srteixeiradias.libraryapi.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;

    public ClientServiceImpl(final ClientRepository clientRepository,
                             final PasswordEncoder encoder) {
        this.clientRepository = clientRepository;
        this.encoder = encoder;
    }

    @Override
    public ClientCreateResponse create(final ClientCreateRequest request) {
        final var client = request.toEntity();
        client.setClientSecret(encoder.encode(request.clientSecret()));
        return ClientCreateResponse.fromEntity(clientRepository.save(client));
    }

    @Override
    public ClientGetResponse findByClientId(final String clientId) {
        return clientRepository.findByClientId(clientId)
                .map(ClientGetResponse::fromEntity)
                .orElseThrow(() -> new NotFoundException("client com o clientId: '" + clientId + "' não encontrado"));
    }
}
