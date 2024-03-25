package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.FavoriteGameDAO;
import tino.playtino.repository.JpaFavoriteGameRepository;

@Component
public class SaveFavoriteGameDAOBean {

    JpaFavoriteGameRepository jpaFavoriteGameRepository;

    @Autowired
    public SaveFavoriteGameDAOBean(JpaFavoriteGameRepository jpaFavoriteGameRepository){
        this.jpaFavoriteGameRepository = jpaFavoriteGameRepository;
    }

    // 게임 DAO 저장
    public void exec(FavoriteGameDAO favoriteGameDAO){
        jpaFavoriteGameRepository.save(favoriteGameDAO);
    }
}
