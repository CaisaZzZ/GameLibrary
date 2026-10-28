package backend.gamelibrary.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.gamelibrary.domain.Game;
import backend.gamelibrary.domain.GameRepository;
import backend.gamelibrary.domain.Genre;
import backend.gamelibrary.domain.GenreRepository;
import backend.gamelibrary.domain.Platform;
import backend.gamelibrary.domain.PlatformRepository;

@Controller
public class MainController {

    private final PlatformRepository platformRepository;
    private final GenreRepository genreRepository;
    public GameRepository gameRepository;

    public MainController(GameRepository gameRepository, GenreRepository genreRepository, PlatformRepository platformRepository){
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
        this.platformRepository = platformRepository;
    }

    @GetMapping("/frontpage")
    public String mainController() {
        return "frontpage"; //frontpage.html
    }

    //Game Library site
    @GetMapping("/library")
    public String libraryController(Model model){
        List<Game> games = (List<Game>) gameRepository.findAll();
        model.addAttribute("games", games);
        return "library"; //library.html
    }

    //CRUD
    @GetMapping("/addgame")
    public String getNewGameForm(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("platforms", platformRepository.findAll());
        return "addgame"; //addgame.html
    }

    @GetMapping("/edit/{id}")
    public String editGame(@PathVariable("id") Long gameId, Model model) {
        Game game = gameRepository.findById(gameId).get();
        model.addAttribute("game", game);
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("platforms", platformRepository.findAll());
        return "addgame";
    }

    @GetMapping("/deletegame/{id}")
    public String deleteGame(@PathVariable("id") Long gameId) {
        gameRepository.deleteById(gameId);
        return "redirect:/library";
    }

    @PostMapping("/savegame")
    public String saveGame(@ModelAttribute Game game, @RequestParam Long genreid, @RequestParam ("gamePlatform") List<Long>gamePlatform) {
        Genre  selectedGenre = genreRepository.findById(genreid).get();
        for (Long platformId : gamePlatform) {
            Platform selectedPlatform = platformRepository.findById(platformId).get();
            game.getPlatforms().add(selectedPlatform);
        }
        game.setGenre(selectedGenre);
        gameRepository.save(game);
        return"redirect:/library";
    }
}
