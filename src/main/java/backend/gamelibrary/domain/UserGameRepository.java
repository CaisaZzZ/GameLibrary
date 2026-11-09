package backend.gamelibrary.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserGameRepository extends CrudRepository <UserGame, Long>{
    List<UserGame> findByUserUsername(String username);
    boolean existsByUserUsernameAndGameId(String username, Long gameId);

    List<UserGame> findByUserUsernameAndStatusName(String username, String statusName);
}
