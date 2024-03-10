package tino.playtino.Bean.Small;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import tino.playtino.domain.Favorite;
import tino.playtino.repository.JpaFavoriteRepository;

import java.util.List;

@Component
public class GetRandomFavoriteDAOsBean {

    JpaFavoriteRepository jpaFavoriteRepository;
    public GetRandomFavoriteDAOsBean(JpaFavoriteRepository jpaFavoriteRepository){
        this.jpaFavoriteRepository = jpaFavoriteRepository;
    }

    // Favorite DAO 랜덤 검색
    public List<Favorite> exec(){
        return jpaFavoriteRepository.findFavorites();
    }

}
