package backend.gamelibrary.web;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import backend.gamelibrary.domain.AppUser;
import backend.gamelibrary.domain.AppUserRepository;
import backend.gamelibrary.domain.Game;
import backend.gamelibrary.domain.GameRepository;
import backend.gamelibrary.domain.Genre;
import backend.gamelibrary.domain.GenreRepository;
import backend.gamelibrary.domain.NewUserForm;
import backend.gamelibrary.domain.Platform;
import backend.gamelibrary.domain.PlatformRepository;
import backend.gamelibrary.domain.Status;
import backend.gamelibrary.domain.StatusRepository;
import backend.gamelibrary.domain.UserGame;
import backend.gamelibrary.domain.UserGameRepository;
import jakarta.validation.Valid;

@Controller
public class MainController {

    private final StatusRepository statusRepository;
    private final PlatformRepository platformRepository;
    private final GenreRepository genreRepository;
    private final GameRepository gameRepository;
    private final AppUserRepository appUserRepository;
    private final UserGameRepository userGameRepository;


    public MainController(GameRepository gameRepository, GenreRepository genreRepository,
            PlatformRepository platformRepository, AppUserRepository appUserRepository, UserGameRepository userGameRepository, StatusRepository statusRepository) {
        this.gameRepository = gameRepository;
        this.genreRepository = genreRepository;
        this.platformRepository = platformRepository;
        this.appUserRepository = appUserRepository;
        this.userGameRepository = userGameRepository;
        this.statusRepository = statusRepository;
    }

    @GetMapping("/frontpage")
    public String mainController() {
        return "frontpage"; // frontpage.html
    }

    // Game Library site
    @GetMapping("/library")
    public String libraryController(Model model) {
        List<Game> games = (List<Game>) gameRepository.findAll();
        model.addAttribute("games", games);
        return "library"; // library.html
    }

    // CRUD
    @GetMapping("/addgame")
    public String getNewGameForm(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("platforms", platformRepository.findAll());
        return "addgame"; // addgame.html
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
    public String saveGame(@ModelAttribute Game game, @RequestParam Long genreid,
            @RequestParam("gamePlatform") List<Long> gamePlatform) {
        Genre selectedGenre = genreRepository.findById(genreid).get();
        for (Long platformId : gamePlatform) {
            Platform selectedPlatform = platformRepository.findById(platformId).get();
            game.getPlatforms().add(selectedPlatform);
        }
        game.setGenre(selectedGenre);
        gameRepository.save(game);
        return "redirect:/library";
    }

    @GetMapping("/login")
    public String logIn() {
        return "login";
    }

    @GetMapping("/userprofile")
    public String personalProfile(Authentication authentication, Model model) {
        String username = authentication.getName();
        AppUser user = appUserRepository.findByUsername(username);
        List<UserGame> userGames = userGameRepository.findByUserUsername(username);

        model.addAttribute("user", user);
        model.addAttribute("userGames", userGames);
        
        return "userprofile";
    }

    @GetMapping("/newuser")
    public String getNewUserForm(Model model) {
        model.addAttribute("newUserForm", new NewUserForm());
        return "newuser";
    }

    @PostMapping("/saveuser")
    public String saveUser(@Valid @ModelAttribute("newUserForm") NewUserForm newUserForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (newUserForm.getPassword().equals(newUserForm.getPasswordCheck())) {
                String pwd = newUserForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                AppUser newUser = new AppUser();
                newUser.setPasswordHash(hashPwd);
                newUser.setUsername(newUserForm.getUsername());
                newUser.setEmail(newUserForm.getEmail());
                newUser.setRole("loggedInUser");
                if (appUserRepository.findByUsername(newUserForm.getUsername()) == null) {
                    appUserRepository.save(newUser);
                } else {
                    bindingResult.rejectValue("username", "err.username", "Username already exsist");
                    return "newuser";
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
                return "newuser";
            }
        } else {
            return "newuser";
        }
        return "redirect:/login";
    }

    @PostMapping("/addtouserlibrary")
    public String addToUserLibrary(@RequestParam Long gameId, @RequestParam Long statusId, Authentication authentication) {
        String username = authentication.getName();
        AppUser user = appUserRepository.findByUsername(username);

        Game game = gameRepository.findById(statusId).get();
        Status status =  statusRepository.findById(statusId).get();
        if (!userGameRepository.existsByUserUsernameAndGameId(username, gameId)) {

            UserGame userGame = new UserGame(user, game, status);
            userGameRepository.save(userGame);
        }

        return "redirect:/library";
    }
}
