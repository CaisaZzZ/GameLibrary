package backend.gamelibrary.web;

import org.springframework.stereotype.Controller;

import backend.gamelibrary.domain.GenreRepository;

@Controller
public class GenreController {

    private GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
}
