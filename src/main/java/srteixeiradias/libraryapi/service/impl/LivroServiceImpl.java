package srteixeiradias.libraryapi.service.impl;

import org.springframework.stereotype.Service;
import srteixeiradias.libraryapi.domain.request.LivroCreateRequest;
import srteixeiradias.libraryapi.domain.response.LivroCreateResponse;
import srteixeiradias.libraryapi.domain.response.LivroGetResponse;
import srteixeiradias.libraryapi.exception.NotFoundException;
import srteixeiradias.libraryapi.repository.AutorRepository;
import srteixeiradias.libraryapi.repository.LivroRepository;
import srteixeiradias.libraryapi.service.LivroService;

import java.util.UUID;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroServiceImpl(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @Override
    public LivroCreateResponse save(LivroCreateRequest request) {
        final var autor = autorRepository.findById(request.autorId())
                .orElseThrow(()-> new NotFoundException("Autor com ID: " + request.autorId() + " não encontrado"));

        return LivroCreateResponse.fromEntity(livroRepository.save(request.toEntity(autor)));
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
}
