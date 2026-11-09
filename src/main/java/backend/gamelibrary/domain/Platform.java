package backend.gamelibrary.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long platformid;
    private String name;

    //table link
    @ManyToMany(mappedBy = "platforms")
    private Set<Game> games = new HashSet<>();

    //constructors
    public Platform () {}

    public Platform(String name){
        this.name = name;
    }

    //getters and setters platformid and name
        public void setPlatformid(Long platformid) {
        this.platformid = platformid;
    }

    public Long getPlatformid() {
        return platformid;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //getters and setters for platform table link
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Platform [name=" + name + "]";
    }

}
