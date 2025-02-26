package srteixeiradias.libraryapi.service.impl;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import srteixeiradias.libraryapi.domain.model.Autor;
import srteixeiradias.libraryapi.domain.request.AutorUpdateRequest;
import srteixeiradias.libraryapi.domain.response.AutorListResponse;
import srteixeiradias.libraryapi.domain.validator.AutorValidator;
import srteixeiradias.libraryapi.exception.NotFoundException;
import srteixeiradias.libraryapi.domain.request.AutorCreateRequest;
import srteixeiradias.libraryapi.domain.response.AutorCreateResponse;
import srteixeiradias.libraryapi.domain.response.AutorGetResponse;
import srteixeiradias.libraryapi.repository.AutorRepository;
import srteixeiradias.libraryapi.service.AutorService;

import java.util.List;
import java.util.UUID;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;
    private final AutorValidator autorValidator;

    public AutorServiceImpl(AutorRepository autorRepository, AutorValidator autorValidator){

        this.autorRepository = autorRepository;
        this.autorValidator = autorValidator;
    }
    
    @Override
    public AutorCreateResponse create(final AutorCreateRequest request) {
        final var autor = request.toEntity();
        autorValidator.validate(autor);
        return AutorCreateResponse.fromEntity(autorRepository.save(autor));
    }

    @Override
    public AutorGetResponse findById(final UUID id) {
        return autorRepository.findById(id)
                .map(AutorGetResponse::fromEntity)
                .orElseThrow(()-> new NotFoundException("Autor com ID: " + id + " não encontrado"));
    }

    @Override
    public void deleteById(final UUID id) {
        final var autor = autorRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Autor com ID: " + id + " não encontrado"));

        autorValidator.validateDelete(autor);
        autorRepository.deleteById(autor.getId());
    }

    @Override
    public List<AutorListResponse> listAutores(final String nome, final String nacionalidade) {
        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);

        ExampleMatcher matcher = ExampleMatcher.matching() // Configura o ExampleMatcher para ignorar campos nulos e permitir busca parcial (LIKE)
                .withIgnoreNullValues()
                .withMatcher("nome", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()) // LIKE e case-insensitive
                .withMatcher("nacionalidade", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase()); // LIKE e case-insensitive

        Example<Autor> example = Example.of(autor, matcher);
        return autorRepository.findAll(example)
                .stream()
                .map(AutorListResponse::fromEntity)
                .toList();
    }

    @Override
    public AutorGetResponse update(String id, AutorUpdateRequest request) {
        var autor = autorRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new NotFoundException("Autor com ID: " + id + " não encontrado"));

        autor.setNome(request.nome());
        autor.setDataNascimento(request.dataNascimento());
        autor.setNacionalidade(request.nacionalidade());

        autorValidator.validate(autor);

        return AutorGetResponse.fromEntity(autorRepository.save(autor));
    }
}
