package backend.gamelibrary.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;
    private String releaseYear;
    private String developer;
    private Double rating;
    private Double price;

    //Consructor
    public Game(String name, String releaseYear, String developer, Double rating, Double price) {
        this.name = name;
        this. releaseYear = releaseYear;
        this. developer = developer;
        this.rating = rating;
        this.price = price;
    }

    //empty constructor for JPA to create objects when retrieving data from the database
    public Game(){}

    //Getters and Setters, toString for Game

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Game [name=" + name + ", platform=" + ", releaseYear=" + releaseYear + ", developer="
                + developer + ", rating=" + rating + ", price=" + price + "]";
    }

}
