package srteixeiradias.libraryapi.domain.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import srteixeiradias.libraryapi.domain.model.Autor;
import srteixeiradias.libraryapi.exception.DomainException;
import srteixeiradias.libraryapi.exception.Error;
import srteixeiradias.libraryapi.repository.AutorRepository;
import srteixeiradias.libraryapi.repository.LivroRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AutorValidator {

    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    public AutorValidator(final AutorRepository autorRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    public void validate(Autor autor) {
        List<Error> errors = new ArrayList<>();

        validateNome(autor.getNome(), errors);
        validateDataNascimento(autor.getDataNascimento(), errors);
        validateNacionalidade(autor.getNacionalidade(), errors);
        validateRepeatedAutor(autor, errors);

        if (!errors.isEmpty())
            throw new DomainException("Erro de validação", errors);

    }

    private void validateNome(String nome, List<Error> errors) {
        if (!StringUtils.hasText(nome)) {
            errors.add(new Error("O nome do autor não pode ser vazio ou nulo."));
        } else if (nome.length() < 3 || nome.length() > 100) {
            errors.add(new Error("O nome do autor deve ter entre 3 e 100 caracteres."));
        }
    }

    private void validateDataNascimento(LocalDate dataNascimento, List<Error> errors) {
        if (dataNascimento == null) {
            errors.add(new Error("A data de nascimento do autor não pode ser nula."));
        } else if (dataNascimento.isAfter(LocalDate.now())) {
            errors.add(new Error("A data de nascimento não pode ser no futuro."));
        }
    }

    private void validateNacionalidade(String nacionalidade, List<Error> errors) {
        if (!StringUtils.hasText(nacionalidade)) {
            errors.add(new Error("A nacionalidade do autor não pode ser vazia ou nula."));
        } else if (nacionalidade.length() < 3 || nacionalidade.length() > 50) {
            errors.add(new Error("A nacionalidade deve ter entre 3 e 50 caracteres."));
        }
    }

    private void validateRepeatedAutor(Autor autor, List<Error> errors) {
        if (autorRepository.existsByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade())) {
            errors.add(new Error("Já existe um autor com o mesmo nome, data de nascimento e nacionalidade."));
        }
    }

    public void validateDelete(Autor autor) {
        if (livroRepository.existsByAutor(autor)){
            throw new DomainException("Erro de deleção",
                    List.of(new Error("O autor possui livros cadastrados e não pode ser deletado."))
            );
        }
    }
}
