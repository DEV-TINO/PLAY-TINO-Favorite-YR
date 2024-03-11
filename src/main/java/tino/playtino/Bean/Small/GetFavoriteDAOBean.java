package tino.playtino.Bean.Small;

import org.springframework.stereotype.Component;
import tino.playtino.domain.Favorite;
import tino.playtino.repository.JpaFavoriteRepository;

import java.util.UUID;

@Component
public class GetFavoriteDAOBean {

    JpaFavoriteRepository jpaFavoriteRepository;
    public GetFavoriteDAOBean(JpaFavoriteRepository jpaFavoriteRepository){
        this.jpaFavoriteRepository = jpaFavoriteRepository;
    }

    public Favorite exec(UUID favoriteId){
        return jpaFavoriteRepository.findById(favoriteId).get();
    }
}
