package srteixeiradias.libraryapi.domain.model;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "autor", schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class Autor {

    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "nome", length = 100, nullable = false)
    private String nome;

    @Column(name= "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name= "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @CreatedDate
    @Column(name= "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name= "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private Usuario user;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", nacionalidade='" + nacionalidade + '\'' +
                '}';
    }
}
