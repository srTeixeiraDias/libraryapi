package srteixeiradias.libraryapi.domain.request;

import jakarta.validation.constraints.NotBlank;
import srteixeiradias.libraryapi.domain.model.Client;

public record ClientCreateRequest(
        @NotBlank(message = "required field")
        String clientId,
        @NotBlank(message = "required field")
        String clientSecret,
        @NotBlank(message = "required field")
        String redirectURI,
        @NotBlank(message = "required field")
        String scope
) {

    public Client toEntity(){
       Client client = new Client();
       client.setClientId(this.clientId);
       client.setClientSecret(this.clientSecret);
       client.setRedirectURI(redirectURI);
       client.setScope(this.scope);
       return client;
    }
}
