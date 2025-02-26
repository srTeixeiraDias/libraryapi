package srteixeiradias.libraryapi.domain.response;

import srteixeiradias.libraryapi.domain.enuns.GeneroLivro;
import srteixeiradias.libraryapi.domain.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroCreateResponse(
        UUID id,
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro genero,
        BigDecimal preco,
        UUID autorId
) {
    public static LivroCreateResponse fromEntity(Livro livro) {
        return new LivroCreateResponse(
                livro.getId(),
                livro.getIsbn(),
                livro.getTitulo(),
                livro.getDataPublicacao(),
                livro.getGeneroLivro(),
                livro.getPreco(),
                livro.getAutor().getId()
        );
    }
}
