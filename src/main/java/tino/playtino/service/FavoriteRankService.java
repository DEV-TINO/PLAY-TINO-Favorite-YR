package tino.playtino.service;

import org.springframework.stereotype.Service;
import tino.playtino.Bean.Small.SaveFavoriteRankDAOBean;
import tino.playtino.domain.DTO.RequestFavoriteRankDTO;
import tino.playtino.domain.FavoriteRank;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.repository.JpaFavoriteRankRepository;

@Service
public class FavoriteRankService {

    JpaFavoriteRankRepository jpaFavoriteRankRepository;
    SaveFavoriteRankDAOBean saveFavoriteRankDAOBean;

    public FavoriteRankService(JpaFavoriteRankRepository jpaFavoriteRankRepository, SaveFavoriteRankDAOBean saveFavoriteRankDAOBean){
        this.jpaFavoriteRankRepository = jpaFavoriteRankRepository;
        this.saveFavoriteRankDAOBean = saveFavoriteRankDAOBean;
    }

    // 랭킹[게임에서 1등한 Favorite] 저장
    public ResponseSuccess saveRank(RequestFavoriteRankDTO requestFavoriteRankDTO){
        // FavoriteRank(DAO) 생성, DTO를 DAO로 변환
        FavoriteRank favoriteRank = new FavoriteRank();
        favoriteRank.setGameId(requestFavoriteRankDTO.getGameId());
        favoriteRank.setFavoriteId(requestFavoriteRankDTO.getFavoriteId());

        // favoriteRank DAO 저장, '성공 여부'를 반환
        return saveFavoriteRankDAOBean.exec(favoriteRank);

    }
}
