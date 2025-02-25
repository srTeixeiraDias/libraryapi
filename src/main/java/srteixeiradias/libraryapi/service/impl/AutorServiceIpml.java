package srteixeiradias.libraryapi.service.impl;

import org.springframework.stereotype.Service;
import srteixeiradias.libraryapi.domain.model.Autor;
import srteixeiradias.libraryapi.domain.request.AutorCreateRequest;
import srteixeiradias.libraryapi.domain.response.AutorCreateResponse;
import srteixeiradias.libraryapi.repository.AutorRepository;
import srteixeiradias.libraryapi.service.AutorService;

@Service
public class AutorServiceIpml implements AutorService {

    private final AutorRepository autorRepository;

    public AutorServiceIpml(AutorRepository autorRepository){

        this.autorRepository = autorRepository;
    }
    
    @Override
    public AutorCreateResponse create(AutorCreateRequest request) {
        Autor autor = request.toEntity();
        autor = autorRepository.save(autor);

        return AutorCreateResponse.fromEntity(autor);
    }
}
