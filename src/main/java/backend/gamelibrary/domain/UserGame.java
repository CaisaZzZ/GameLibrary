package backend.gamelibrary.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

@Entity
public class UserGame {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)

    private Long id;

    @ManyToOne
    private AppUser user;

    @ManyToOne
    private Game game;

    @ManyToOne
    private Status status;

    public UserGame(){}

    public UserGame(AppUser user, Game game, Status status){
        this.user = user;
        this.game = game;
        this.status = status;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
