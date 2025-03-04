package srteixeiradias.libraryapi.domain.response;

import java.util.List;

public record LivroSearchResponse(
        List<LivroGetResponse> livros,
        int currentPage,
        int totalPages,
        long totalElements
) {}
