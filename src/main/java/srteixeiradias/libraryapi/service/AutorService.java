package srteixeiradias.libraryapi.service;

import srteixeiradias.libraryapi.domain.request.AutorCreateRequest;
import srteixeiradias.libraryapi.domain.response.AutorCreateResponse;

public interface AutorService {

    AutorCreateResponse create (AutorCreateRequest request);

}
