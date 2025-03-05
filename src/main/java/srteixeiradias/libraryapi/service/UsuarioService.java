package srteixeiradias.libraryapi.service;

import srteixeiradias.libraryapi.domain.request.UsuarioCreateRequest;
import srteixeiradias.libraryapi.domain.response.UsuarioCreateResponse;

public interface UsuarioService {

    UsuarioCreateResponse create (final UsuarioCreateRequest request);
}
