package srteixeiradias.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import srteixeiradias.libraryapi.domain.model.Client;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findByClientId(String clientId);
}
