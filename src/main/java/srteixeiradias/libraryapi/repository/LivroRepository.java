package srteixeiradias.libraryapi.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    boolean existsByIsbn(String isbn);

    @Query("""
    SELECT l FROM Livro l
    JOIN FETCH l.autor a
    WHERE (:isbn IS NULL OR l.isbn ILIKE %:isbn%)
      AND (:titulo IS NULL OR l.titulo ILIKE %:titulo%)
      AND (:genero IS NULL OR l.generoLivro = :genero)
      AND (:ano IS NULL OR EXTRACT(YEAR FROM l.dataPublicacao) = :ano)
      AND (:nomeAutor IS NULL OR a.nome ILIKE %:nomeAutor%)
""")
    Page<Livro> buscarLivros(
            @Param("isbn") String isbn,
            @Param("titulo") String titulo,
            @Param("genero") GeneroLivro genero,
            @Param("ano") Integer ano,
            @Param("nomeAutor") String nomeAutor,
            Pageable pageable
    );




}