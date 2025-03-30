package srteixeiradias.libraryapi.domain.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="client")
public class Client {
    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "client_id")
    private String clientId;

    @Column(name= "client_secret")
    private String clientSecret;

    @Column(name= "redirect_uri")
    private String redirectURI;

    @Column(name= "scope")
    private String scope;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
