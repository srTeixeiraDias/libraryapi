package srteixeiradias.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import srteixeiradias.libraryapi.model.Autor;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
