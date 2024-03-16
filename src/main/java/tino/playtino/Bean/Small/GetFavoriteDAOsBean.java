package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.Favorite;
import tino.playtino.repository.JpaFavoriteRepository;

import java.util.List;

@Component
public class GetFavoriteDAOsBean {

    JpaFavoriteRepository jpaFavoriteRepository;

    @Autowired
    public GetFavoriteDAOsBean(JpaFavoriteRepository jpaFavoriteRepository){
        this.jpaFavoriteRepository = jpaFavoriteRepository;
    }

    public List<Favorite> exec(){
        return jpaFavoriteRepository.findAll();
    }
}
