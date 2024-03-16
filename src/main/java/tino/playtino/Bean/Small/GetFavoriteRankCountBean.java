package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.repository.JpaFavoriteRankRepository;

import java.util.UUID;

@Component
public class GetFavoriteRankCountBean {

    JpaFavoriteRankRepository jpaFavoriteRankRepository;

    @Autowired
    public GetFavoriteRankCountBean(JpaFavoriteRankRepository jpaFavoriteRankRepository){
        this.jpaFavoriteRankRepository = jpaFavoriteRankRepository;
    }

    // FavoriteRank 전체 레코드 수 조회
    public Integer exec(){
        return (int) jpaFavoriteRankRepository.count();
    }

    // FavoriteRank에서 favoriteId와 일치하는 레코드 수 조회
    public Integer exec(UUID favoriteId){
        return jpaFavoriteRankRepository.findByFavoriteId(favoriteId).size();
    }
}
