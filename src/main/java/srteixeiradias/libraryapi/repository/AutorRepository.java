package srteixeiradias.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import srteixeiradias.libraryapi.domain.model.Autor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

    boolean existsByNomeAndDataNascimentoAndNacionalidade(String nome,
                                                               LocalDate dataNascimento,
                                                               String nacionalidade);
}
