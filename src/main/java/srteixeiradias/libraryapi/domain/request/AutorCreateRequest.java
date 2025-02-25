package srteixeiradias.libraryapi.domain.request;

import srteixeiradias.libraryapi.domain.model.Autor;

import java.time.LocalDate;

public record AutorCreateRequest(
        String nome,
        LocalDate dataNascimento,
        String nacionalidade

) {

    public  Autor toEntity() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }

}
