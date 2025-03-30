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

    public Client toEntity(){
        Client client = new Client();
        client.setId(this.id);
        client.setClientId(this.cliendId);
        client.setClientSecret(this.clientSecret);
        client.setRedirectURI(this.redirectURI);
        client.setScope(this.scope);

        return client;
    }
}
