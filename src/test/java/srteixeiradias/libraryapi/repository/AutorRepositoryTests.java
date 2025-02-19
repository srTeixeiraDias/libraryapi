package srteixeiradias.libraryapi.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import srteixeiradias.libraryapi.model.Autor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class AutorRepositoryTests {
    @Autowired
    AutorRepository autorRepository;

    @Test
    void testSaveAutor(){
        var autor = new Autor();
        autor.setNome("Bernardo Teixeira");
        autor.setDataNascimento(LocalDate.of(2016,11,16));
        autor.setNacionalidade("Brasileiro");

        var autorSalvo = autorRepository.save(autor);
        System.out.println("autor salvo:" + autorSalvo);

        Assertions.assertNotNull(autorSalvo.getId(), "O ID do autor salvo não pode ser nulo");
        Assertions.assertEquals("Bernardo Teixeira", autorSalvo.getNome(), "O nome do autor salvo deve ser 'Bernardo Teixeira'");
    }

    @Test
    void testAtualizarAutor(){
        var id = UUID.fromString("dd09da00-8d34-4747-a5ee-796f55bb0af6");

        var autor = autorRepository.findById(id).orElseThrow();

        autor.setNome("Samuel Dias");

        var autorAtualizado = autorRepository.save(autor);
        Assertions.assertEquals("Samuel Dias", autorAtualizado.getNome(),
                "O nome do autor deve ter sido atualizado para 'Samuel Dias'");

    }

    @Test
    void testListar(){

        List<Autor> autores = autorRepository.findAll();

        for(Autor autor : autores){
            System.out.println("Autor: " + autor);
        }
    }

    @Test
    void testDeletarPorId(){
        var id = UUID.fromString("dd09da00-8d34-4747-a5ee-796f55bb0af6");

        autorRepository.deleteById(id);
    }

    @Test
    void testDeletarObj(){
        var id = UUID.fromString("dd09da00-8d34-4747-a5ee-796f55bb0af6");
        var autor = autorRepository.findById(id).orElseThrow();

        autorRepository.delete(autor);

    }
}
