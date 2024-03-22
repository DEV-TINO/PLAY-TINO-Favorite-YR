package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.FavoriteCount;
import tino.playtino.repository.JpaFavoriteCountRepository;

@Component
public class SaveFavoriteCountDAOBean {

    JpaFavoriteCountRepository jpaFavoriteCountRepository;

    @Autowired
    public SaveFavoriteCountDAOBean(JpaFavoriteCountRepository jpaFavoriteCountRepository){
        this.jpaFavoriteCountRepository = jpaFavoriteCountRepository;
    }

    // DAO 저장
    public void exec(FavoriteCount favoriteCount){
        jpaFavoriteCountRepository.save(favoriteCount);
    }
}
