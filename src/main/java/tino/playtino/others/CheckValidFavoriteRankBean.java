package tino.playtino.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.Bean.Small.GetFavoriteDAOBean;
import tino.playtino.Bean.Small.GetFavoriteGameDAOBean;
import tino.playtino.domain.FavoriteRank;

@Component
public class CheckValidFavoriteRankBean {

    GetFavoriteGameDAOBean getFavoriteGameDAOBean;
    GetFavoriteDAOBean getFavoriteDAOBean;

    @Autowired
    public CheckValidFavoriteRankBean(GetFavoriteGameDAOBean getFavoriteGameDAOBean, GetFavoriteDAOBean getFavoriteDAOBean){
        this.getFavoriteGameDAOBean = getFavoriteGameDAOBean;
        this.getFavoriteDAOBean = getFavoriteDAOBean;
    }

    // FavoriteRank DAO 값이 유효한지 확인하는 기능
    public Boolean exec(FavoriteRank favoriteRank){

        // gameId가 존재하는 게임인지 확인하기 위해 gameId로 FavoriteGame 검색하고
        // favoriteId가 존재하는 컨텐트인지 확인하기 위해 favoriteId로 Favorite 검색
        // -> 둘 다 null이 아니면 true 반환
        if(
                getFavoriteGameDAOBean.exec(favoriteRank.getGameId()) != null
                && getFavoriteDAOBean.exec(favoriteRank.getFavoriteId()) != null
        ) {
            return true;
        }

        // null이 하나라도 있다면 false 반환
        else return false;

    }
}
