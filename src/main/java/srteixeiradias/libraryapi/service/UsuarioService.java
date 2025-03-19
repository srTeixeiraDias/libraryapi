package srteixeiradias.libraryapi.service;

import srteixeiradias.libraryapi.domain.request.UsuarioCreateRequest;
import srteixeiradias.libraryapi.domain.response.UsuarioCreateResponse;
import srteixeiradias.libraryapi.domain.response.UsuarioGetResponse;

public interface UsuarioService {

    UsuarioCreateResponse create (final UsuarioCreateRequest request);

    UsuarioGetResponse findByLogin (final String login);

    UsuarioGetResponse findByEmail (final String email);
}
