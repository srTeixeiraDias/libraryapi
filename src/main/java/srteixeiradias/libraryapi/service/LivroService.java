package srteixeiradias.libraryapi.service;

import org.springframework.data.domain.Page;
import srteixeiradias.libraryapi.domain.request.LivroCreateRequest;
import srteixeiradias.libraryapi.domain.request.LivroSearchRequest;
import srteixeiradias.libraryapi.domain.request.LivroUpdateRequest;
import srteixeiradias.libraryapi.domain.response.LivroCreateResponse;
import srteixeiradias.libraryapi.domain.response.LivroGetResponse;
import srteixeiradias.libraryapi.domain.response.LivroSearchResponse;

import java.util.UUID;

public interface LivroService {

    LivroCreateResponse save(final LivroCreateRequest request);

    LivroGetResponse findById(final UUID id);

    void deleteById(final UUID id);

    LivroGetResponse update(final UUID id, final LivroUpdateRequest request);

    Page<LivroSearchResponse> buscarLivros(final LivroSearchRequest request);
}
