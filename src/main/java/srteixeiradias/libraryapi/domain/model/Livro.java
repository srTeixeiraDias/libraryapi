package srteixeiradias.libraryapi.domain.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import srteixeiradias.libraryapi.domain.enuns.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name= "livro")
@EntityListeners(AuditingEntityListener.class)
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

    @ManyToOne(fetch = FetchType.LAZY)//(cascade = CascadeType.ALL)
    @JoinColumn(name="id_autor")
    private Autor autor;

    @CreatedDate
    @Column(name= "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name= "updated_at")
    private LocalDateTime updatedAt;

    @Column(name= "user_id")
    private UUID userId;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public UUID getUserId() {
        return userId;
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
