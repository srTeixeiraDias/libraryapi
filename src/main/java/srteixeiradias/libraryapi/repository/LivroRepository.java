package srteixeiradias.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import srteixeiradias.libraryapi.model.Livro;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
