package srteixeiradias.libraryapi.service;

import srteixeiradias.libraryapi.domain.request.LivroCreateRequest;
import srteixeiradias.libraryapi.domain.response.LivroCreateResponse;

public interface LivroService {

    LivroCreateResponse save (LivroCreateRequest request);
}
