package srteixeiradias.libraryapi.domain.validator;

import org.springframework.stereotype.Component;
import srteixeiradias.libraryapi.domain.model.Livro;
import srteixeiradias.libraryapi.exception.DomainException;
import srteixeiradias.libraryapi.exception.Error;
import srteixeiradias.libraryapi.repository.LivroRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class LivroValidator {

    private final LivroRepository livroRepository;

    private static final int ANO_EXIGENCIA_PRECO = 2020;


    public LivroValidator(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void validate(Livro livro){
        List<Error> errors = new ArrayList<>();

        validateIsbn(livro.getIsbn(), errors);
        validadeDataPublicacaoEPreco(livro, errors);
        validateDataPublicacao(livro.getDataPublicacao(), errors);

        if (!errors.isEmpty())
            throw new DomainException("Erro de validação", errors);

    }

    public void validateIsbn(String isbn, List<Error> errors){
        if(livroRepository.existsByIsbn(isbn))
            errors.add(new Error("Não é possivel possuir um livro com um ISBN já cadastrado"));
    }

    public void validadeDataPublicacaoEPreco(Livro livro, List<Error> errors){
        if(livro.getDataPublicacao().getYear() >= ANO_EXIGENCIA_PRECO && livro.getPreco() == null)
                errors.add(new Error("Livros a partir de 2020 precisam ter seus preços informados"));
    }

    public void validateDataPublicacao(LocalDate dataPublicacao, List<Error> errors){
        if(dataPublicacao.isAfter(LocalDate.now()))
            errors.add(new Error("A data de publicação não pode ser uma data futura"));
    }


}
