package srteixeiradias.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import srteixeiradias.libraryapi.domain.model.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByLogin(String login);

    Optional<Usuario> findByEmail(String email);
}
