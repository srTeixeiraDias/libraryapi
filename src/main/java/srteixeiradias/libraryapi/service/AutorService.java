package srteixeiradias.libraryapi.service;

import srteixeiradias.libraryapi.domain.request.AutorCreateRequest;
import srteixeiradias.libraryapi.domain.request.AutorUpdateRequest;
import srteixeiradias.libraryapi.domain.response.AutorCreateResponse;
import srteixeiradias.libraryapi.domain.response.AutorGetResponse;
import srteixeiradias.libraryapi.domain.response.AutorListResponse;

import java.util.List;
import java.util.UUID;

public interface AutorService {

    AutorCreateResponse create(final AutorCreateRequest request);

    AutorGetResponse findById(final UUID id);

    void deleteById(final UUID id);

    List<AutorListResponse> listAutores(final String nome, final String nacionalidade);

    AutorGetResponse update(final String id, final AutorUpdateRequest request);
}
