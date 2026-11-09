package backend.gamelibrary.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.gamelibrary.domain.Game;
import backend.gamelibrary.domain.GameRepository;

@RestController
public class GameRestController {

    private final GameRepository gameRepository;

    public GameRestController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @GetMapping("/api/games")
    public Iterable<Game>getAllGames() {
        return gameRepository.findAll();
    }
}
