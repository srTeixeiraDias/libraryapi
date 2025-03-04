package srteixeiradias.libraryapi.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import srteixeiradias.libraryapi.domain.model.Livro;
import srteixeiradias.libraryapi.domain.request.LivroCreateRequest;
import srteixeiradias.libraryapi.domain.request.LivroSearchRequest;
import srteixeiradias.libraryapi.domain.request.LivroUpdateRequest;
import srteixeiradias.libraryapi.domain.response.LivroCreateResponse;
import srteixeiradias.libraryapi.domain.response.LivroGetResponse;
import srteixeiradias.libraryapi.domain.response.LivroSearchResponse;
import srteixeiradias.libraryapi.domain.validator.LivroValidator;
import srteixeiradias.libraryapi.exception.NotFoundException;
import srteixeiradias.libraryapi.repository.AutorRepository;
import srteixeiradias.libraryapi.repository.LivroRepository;
import srteixeiradias.libraryapi.service.LivroService;

import java.util.List;
import java.util.UUID;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final LivroValidator livroValidator;

    public LivroServiceImpl(LivroRepository livroRepository, AutorRepository autorRepository, LivroValidator livroValidator) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.livroValidator = livroValidator;
    }

    @Override
    public LivroCreateResponse save(LivroCreateRequest request) {
        final var autor = autorRepository.findById(request.autorId())
                .orElseThrow(()-> new NotFoundException("Autor com ID: " + request.autorId() + " não encontrado"));

        final var livro = request.toEntity(autor);
        livroValidator.validate(livro);

        return LivroCreateResponse.fromEntity(livroRepository.save(livro));
    }

    @Override
    public LivroGetResponse findById(UUID id) {
        return livroRepository.findById(id)
                .map(LivroGetResponse::fromEntity)
                .orElseThrow(()-> new NotFoundException("Livro com ID: " + id + " não encontrado"));
    }

    @Override
    public void deleteById(UUID id) {
        var livro = livroRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Livro com ID: " + id + " não encontrado"));

        livroRepository.deleteById(livro.getId());
    }

    @Override
    public LivroGetResponse update(UUID id, LivroUpdateRequest request) {
        var livro = livroRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Livro com ID: " + id + " não encontrado"));

        final var autor = autorRepository.findById(request.autorId())
                .orElseThrow(()-> new NotFoundException("Autor com ID: " + request.autorId() + " não encontrado"));

        livro.setIsbn(request.isbn());
        livro.setTitulo(request.titulo());
        livro.setDataPublicacao(request.dataPublicacao());
        livro.setGeneroLivro(request.generoLivro());
        livro.setPreco(request.preco());
        livro.setAutor(autor);

        livroValidator.validate(livro);

        return LivroGetResponse.fromEntity(livroRepository.save(livro));

    }

    @Override
    public Page<LivroSearchResponse> buscarLivros(LivroSearchRequest request) {
        Pageable pageable = PageRequest.of(
                request.page() != null ? request.page() : 0,
                request.size() != null ? request.size() : 2);

        Page<Livro> livros = livroRepository.buscarLivros(request.isbn(),
                request.titulo(), request.genero(), request.ano(), request.nomeAutor(), pageable);

        List<LivroGetResponse> livroResponses = livros.getContent()
                .stream()
                .map(LivroGetResponse::fromEntity)
                .toList();

        return new PageImpl(livroResponses, pageable, livros.getTotalElements());
    }
}
