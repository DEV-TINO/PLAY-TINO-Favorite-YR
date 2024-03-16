package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.FavoriteGame;
import tino.playtino.repository.JpaFavoriteGameRepository;

import java.util.UUID;

@Component
public class GetFavoriteGameDAOBean {

    JpaFavoriteGameRepository jpaFavoriteGameRepository;

    @Autowired
    public GetFavoriteGameDAOBean(JpaFavoriteGameRepository jpaFavoriteGameRepository){
        this.jpaFavoriteGameRepository = jpaFavoriteGameRepository;
    }

    public FavoriteGame exec(UUID gameId){
        return jpaFavoriteGameRepository.findById(gameId).get();
    }
}
