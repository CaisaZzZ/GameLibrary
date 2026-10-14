package backend.gamelibrary.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import backend.gamelibrary.domain.Game;
import backend.gamelibrary.domain.GameRepository;

@Controller
public class MainController {

    public GameRepository gameRepository;

    public MainController(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @GetMapping("/frontpage")
    public String mainController() {
        return "frontpage"; //frontpage.html
    }

    //Game Library View html
    @GetMapping("/library")
    public String libraryController(Model model){
        List<Game> games = (List<Game>) gameRepository.findAll();
        model.addAttribute("games", games);
        return "library"; //library.html
    }

}
