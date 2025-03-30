package srteixeiradias.libraryapi.domain.response;

import srteixeiradias.libraryapi.domain.model.Client;

import java.util.UUID;

public record ClientCreateResponse(
        UUID id,
        String clientId,
        String clientSecret,
        String redirectURI,
        String scope
) {

    public static ClientCreateResponse fromEntity(Client client){
        return new ClientCreateResponse(
                client.getId(),
                client.getClientId(),
                client.getClientSecret(),
                client.getRedirectURI(),
                client.getScope()
        );
    }
}
