package backend.gamelibrary.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long genreid;
    private String name;

    //table link
    @OneToMany(mappedBy="genre")
    private List<Game> games;

    public Genre() {}

    public Genre(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGenreid() {
        return genreid;
    }

    public List<Game>getGames(){
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Genre [name=" + name + "]";
    }
}
