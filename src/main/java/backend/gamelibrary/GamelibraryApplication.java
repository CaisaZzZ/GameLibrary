package backend.gamelibrary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.gamelibrary.domain.Game;
import backend.gamelibrary.domain.GameRepository;

@SpringBootApplication
public class GamelibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamelibraryApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(GameRepository repository) {
        return (args) -> {

            //test data
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

            repository.save(a);
            repository.save(b);
            repository.save(c);

        };
    }

}
