package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.FavoriteDAO;
import tino.playtino.repository.JpaFavoriteRepository;

import java.util.UUID;

@Component
public class GetFavoriteDAOBean {

    JpaFavoriteRepository jpaFavoriteRepository;

    @Autowired
    public GetFavoriteDAOBean(JpaFavoriteRepository jpaFavoriteRepository){
        this.jpaFavoriteRepository = jpaFavoriteRepository;
    }

    public FavoriteDAO exec(UUID favoriteId){
        return jpaFavoriteRepository.findById(favoriteId).get();
    }
}
