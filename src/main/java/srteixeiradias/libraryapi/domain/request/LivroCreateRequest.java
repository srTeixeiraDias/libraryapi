package srteixeiradias.libraryapi.domain.request;

import srteixeiradias.libraryapi.domain.enuns.GeneroLivro;
import srteixeiradias.libraryapi.domain.model.Autor;
import srteixeiradias.libraryapi.domain.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroCreateRequest(
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro generoLivro,
        BigDecimal preco,
        Autor autor
) {

    public Livro toEntity(){
        Livro livro = new Livro();
        livro.setIsbn(this.isbn);
        livro.setTitulo(this.titulo);
        livro.setDataPublicacao(this.dataPublicacao);
        livro.setGeneroLivro(this.generoLivro);
        livro.setPreco(this.preco);
        livro.setAutor(this.autor);

        return livro;
    }
}
