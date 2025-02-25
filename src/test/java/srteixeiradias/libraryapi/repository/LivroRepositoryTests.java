package srteixeiradias.libraryapi.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import srteixeiradias.libraryapi.domain.enuns.GeneroLivro;
import srteixeiradias.libraryapi.domain.model.Autor;
import srteixeiradias.libraryapi.domain.model.Livro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
        livro.setTitulo("A lalala");
        livro.setDataPublicacao(LocalDate.of(2025, 1, 3));
        livro.setGeneroLivro(GeneroLivro.FANTASIA);
        livro.setPreco(BigDecimal.valueOf(35,61));
        //livro.setAutor(autor);

        var livroSalvo = livroRepository.save(livro);
        System.out.println("Livro salvo:" + livroSalvo);

        Assertions.assertNotNull(livroSalvo.getId(), "O ID do livro salvo não pode ser nulo");
        Assertions.assertEquals("123456", livroSalvo.getIsbn());
        //Assertions.assertEquals(autor, livroSalvo.getAutor());
    }

    @Test
    void testSalvarLivroCascade(){

        Autor autor = new Autor();
        autor.setNome("Josefaldo Pereira");
        autor.setDataNascimento(LocalDate.of(1899,10,4));
        autor.setNacionalidade("Portuguesa");

        var livro = new Livro();
        livro.setIsbn("7654321");
        livro.setTitulo("A cheiro de rato");
        livro.setDataPublicacao(LocalDate.of(1925, 2, 3));
        livro.setGeneroLivro(GeneroLivro.MISTERIO);
        livro.setPreco(BigDecimal.valueOf(98,50));
        livro.setAutor(autor);

        var livroSalvo = livroRepository.save(livro);
        System.out.println("Livro salvo:" + livroSalvo);

        Assertions.assertNotNull(livroSalvo.getId(), "O ID do livro salvo não pode ser nulo");
        Assertions.assertEquals("7654321", livroSalvo.getIsbn());
        Assertions.assertEquals(autor, livroSalvo.getAutor());
    }

    @Test
    void testDeletarLivro(){
        var id = UUID.fromString("d84e558f-3520-433a-9652-f9b1ff5a7d61");

        livroRepository.deleteById(id);
    }


    @Test
    void testAtualizarLivro(){
        var id = UUID.fromString("d5147124-770f-4482-ad66-64ef06c351ae");

        var livro = livroRepository.findById(id).orElseThrow();

        livro.setIsbn("1234567");

        var livroatualizado = livroRepository.save(livro);
        Assertions.assertEquals("1234567", livroatualizado.getIsbn(),
                "O isbn deve ter sido atualizado para '1234567'");
    }

    @Test
    void testListarLivro(){

        List<Livro> livros = livroRepository.findAll();

        for(Livro livro : livros){
            System.out.println("livro: " + livro);
        }
    }

    @Test
    void testDeletarPorId(){

        var id = UUID.fromString("aa31f11b-2351-4973-b464-2fe3833fd453");

        livroRepository.deleteById(id);

    }

    @Test
    void testDeletarObj(){

        var id = UUID.fromString("aa31f11b-2351-4973-b464-2fe3833fd453");

        var livro = livroRepository.findById(id).orElseThrow();

        livroRepository.delete(livro);

    }

    @Test
    void testListarGenerosDeAutoresBrasileiros(){
        List<String> generos = livroRepository.listarGenerosdeAutoresBrasileiros();

        generos.forEach(System.out::println);
    }

    @Test
    void testDeletarPorGenero(){
        livroRepository.deletarPorGenero(GeneroLivro.FANTASIA);
    }

}
