package srteixeiradias.libraryapi.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import srteixeiradias.libraryapi.model.Genero;
import srteixeiradias.libraryapi.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTests {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void testSalvarLivro(){

       var autor = autorRepository.findById(UUID.fromString("dd09da00-8d34-4747-a5ee-796f55bb0af6"))
               .orElseThrow();

        var livro = new Livro();
        livro.setIsbn("123456");
        livro.setTitulo("A Cura");
        livro.setDataPublicacao(LocalDate.of(2025, 1, 3));
        livro.setGenero(Genero.FICCAO);
        livro.setPreco(BigDecimal.valueOf(35,61));
        livro.setAutor(autor);

        var livroSalvo = livroRepository.save(livro);
        System.out.println("Livro salvo:" + livroSalvo);

        Assertions.assertNotNull(livroSalvo.getId(), "O ID do livro salvo não pode ser nulo");
        Assertions.assertEquals("123456", livroSalvo.getIsbn());
        Assertions.assertEquals(autor, livroSalvo.getAutor());
    }
}
