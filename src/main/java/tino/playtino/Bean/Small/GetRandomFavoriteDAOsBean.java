package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.Favorite;
import tino.playtino.repository.JpaFavoriteRepository;

import java.util.List;

@Component
public class GetRandomFavoriteDAOsBean {

    JpaFavoriteRepository jpaFavoriteRepository;

    @Autowired
    public GetRandomFavoriteDAOsBean(JpaFavoriteRepository jpaFavoriteRepository){
        this.jpaFavoriteRepository = jpaFavoriteRepository;
    }

    // Favorite DAO 랜덤 검색
    public List<Favorite> exec(){
        return jpaFavoriteRepository.findFavorites();
    }

}
