package srteixeiradias.libraryapi.domain.response;

import srteixeiradias.libraryapi.domain.model.Client;

import java.util.UUID;

public record ClientGetResponse(
        UUID id,
        String cliendId,
        String clientSecret,
        String redirectURI,
        String scope
) {

    public static ClientGetResponse fromEntity(Client client){
        return new ClientGetResponse(
                client.getId(),
                client.getClientId(),
                client.getClientSecret(),
                client.getRedirectURI(),
                client.getScope()
        );
    }
}
