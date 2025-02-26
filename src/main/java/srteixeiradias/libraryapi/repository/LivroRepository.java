package srteixeiradias.libraryapi.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import srteixeiradias.libraryapi.domain.enuns.GeneroLivro;
import srteixeiradias.libraryapi.domain.model.Autor;
import srteixeiradias.libraryapi.domain.model.Livro;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {


    @Query("""
        SELECT l.generoLivro
        FROM Livro l
        JOIN l.autor a 
        WHERE a.nacionalidade = "Brasileiro" 
        OR a.nacionalidade = "Brasileira"
        ORDER BY l.generoLivro
    """)
    List<String> listarGenerosdeAutoresBrasileiros();

    @Modifying
    @Transactional
    @Query("delete from Livro l where l.generoLivro = :generoLivro")
    void deletarPorGenero(@Param("generoLivro") GeneroLivro generoLivro);

    boolean existsByAutor(Autor autor);
}
