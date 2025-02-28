package srteixeiradias.libraryapi.domain.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.ISBN;
import srteixeiradias.libraryapi.domain.enuns.GeneroLivro;
import srteixeiradias.libraryapi.domain.model.Autor;
import srteixeiradias.libraryapi.domain.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record LivroCreateRequest(
        @ISBN
        @NotBlank(message = "required field")
        @Size(min= 3, max = 20, message = "field outside standard size")
        String isbn,

        @NotBlank(message = "required field")
        @Size(min= 3, max = 100, message = "field outside standard size")
        String titulo,

        @NotNull(message = "required field")
        @Past(message = "date cannot be future")
        LocalDate dataPublicacao,

        @NotNull(message = "required field")
        GeneroLivro generoLivro,


        BigDecimal preco,

        @NotNull(message = "required field")
        UUID autorId
) {
    public Livro toEntity(Autor autor){
        Livro livro = new Livro();
        livro.setIsbn(this.isbn);
        livro.setTitulo(this.titulo);
        livro.setDataPublicacao(this.dataPublicacao);
        livro.setGeneroLivro(this.generoLivro);
        livro.setPreco(this.preco);
        livro.setAutor(autor);

        return livro;
    }
}
