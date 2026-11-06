package backend.gamelibrary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.gamelibrary.domain.AppUser;
import backend.gamelibrary.domain.AppUserRepository;
import backend.gamelibrary.domain.Game;
import backend.gamelibrary.domain.GameRepository;
import backend.gamelibrary.domain.Genre;
import backend.gamelibrary.domain.GenreRepository;
import backend.gamelibrary.domain.Platform;
import backend.gamelibrary.domain.PlatformRepository;
import backend.gamelibrary.domain.Status;
import backend.gamelibrary.domain.StatusRepository;



@SpringBootApplication
public class GamelibraryApplication {

	private final StatusRepository statusRepository;

    GamelibraryApplication(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(GamelibraryApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(GameRepository repository, GenreRepository genreRepository, PlatformRepository platformRepository, AppUserRepository appUserRepository) {
        return (args) -> {

            //test data
            //GENRE
            Genre RPG = new Genre("RPG");
            Genre Sandbox = new Genre ("Sandbox");
            Genre FPS = new Genre ("FPS");

            genreRepository.save(RPG);
            genreRepository.save(Sandbox);
            genreRepository.save(FPS);

            //PLATFORM
            Platform PC = new Platform("PC");
            Platform PS5 = new Platform("PS5");
            Platform XBOX = new Platform("XBOX");

            platformRepository.save(PC);
            platformRepository.save(PS5);
            platformRepository.save(XBOX);

            //GAMES
            Game a = new Game(
                "The Witcher 3: Wild Hunt",
                "2015",
                "CD Projekt",
                9.1,
                49.99);

            Game b = new Game(
                "Minecraft",
                "2011",
                "Mojang",
                8.4,
                39.99 );

            Game c = new Game(
                "Counter-Strike 2",
                "2023",
                "Valve Corporation",
                5.5,
                0.0 );

            //Genres and Platforms for test Games
            a.getPlatforms().add(PC);
            a.getPlatforms().add(PS5);
            a.getPlatforms().add(XBOX);

            b.getPlatforms().add(PC);

            c.getPlatforms().add(PC);

            a.setGenre(RPG);
            b.setGenre(Sandbox);
            c.setGenre(FPS);

            repository.save(a);
            repository.save(b);
            repository.save(c);

            //TEST USERS
            AppUser user1 = new AppUser("TestAdmin", "$2a$10$ikhwWryRsCotLO9JfXLep.Pu4RLqshAreha2n/wsE1lT/m6WBYgXm", "testadmin@email.com", "admin" );
            AppUser user2 = new AppUser("LoggedInUser", "$2a$10$HKk.3K9/wvrcQ/Y3YlWoWeJjVlWysOLRDDlKsiGXK/FDoGxfZgqGS", "loggedinuser@email.com", "loggedInUser");


            appUserRepository.save(user1);
            appUserRepository.save(user2);

            //GAME STATUS
            Status status1 = new Status("Completed");
            Status status2 = new Status("Wishlist");
            Status status3 = new Status("Currently playing");
            Status status4 = new Status("Dropped");

            statusRepository.save(status1);
            statusRepository.save(status2);
            statusRepository.save(status3);
            statusRepository.save(status4);
        };
    }
}