package srteixeiradias.libraryapi.domain.response;

import srteixeiradias.libraryapi.domain.enuns.GeneroLivro;
import srteixeiradias.libraryapi.domain.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroGetResponse(
       UUID id,
       String isbn,
       String titulo,
       LocalDate dataPublicacao,
       GeneroLivro genero,
       BigDecimal preco,
       LivroAutorGetResponse autor
) {
    public static LivroGetResponse from(Livro livro) {
        return new LivroGetResponse(
                livro.getId(),
                livro.getIsbn(),
                livro.getTitulo(),
                livro.getDataPublicacao(),
                livro.getGeneroLivro(),
                livro.getPreco(),
                LivroAutorGetResponse.from(livro.getAutor())
        );
    }
}