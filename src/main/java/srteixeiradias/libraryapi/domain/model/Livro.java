package srteixeiradias.libraryapi.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import srteixeiradias.libraryapi.domain.enuns.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name= "livro")
public class Livro {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name="titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name="data_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @Column(name="preco", precision = 18, scale = 2, nullable = false)
    private BigDecimal preco;

    @Column(name="genero", nullable = false)
    @Enumerated(EnumType.STRING)
    private GeneroLivro generoLivro;

    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name="id_autor")
    private Autor autor;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public GeneroLivro getGeneroLivro() {
        return generoLivro;
    }

    public void setGeneroLivro(GeneroLivro generoLivro) {
        this.generoLivro = generoLivro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                ", preco=" + preco +
                ", genero=" + generoLivro +
                ", autor=" + autor +
                '}';
    }
}
