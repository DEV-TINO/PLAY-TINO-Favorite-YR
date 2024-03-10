package tino.playtino.service;

import org.springframework.stereotype.Service;
import tino.playtino.Bean.Small.GetRandomFavoriteDAOsBean;
import tino.playtino.Bean.Small.SaveFavoriteGameDAOBean;
import tino.playtino.domain.FavoriteGame;
import tino.playtino.domain.DTO.ResponseFavoriteGameDTO;
import tino.playtino.repository.JpaFavoriteGameRepository;

import java.util.UUID;

@Service
public class FavoriteGameService {

    JpaFavoriteGameRepository jpaFavoriteGameRepository;
    GetRandomFavoriteDAOsBean getRandomFavoriteDAOsBean;
    SaveFavoriteGameDAOBean saveFavoriteGameDAOBean;

    public FavoriteGameService(JpaFavoriteGameRepository jpaFavoriteGameRepository, GetRandomFavoriteDAOsBean getRandomFavoriteDAOsBean, SaveFavoriteGameDAOBean saveFavoriteGameDAOBean){
        this.jpaFavoriteGameRepository = jpaFavoriteGameRepository;
        this.getRandomFavoriteDAOsBean = getRandomFavoriteDAOsBean;
        this.saveFavoriteGameDAOBean = saveFavoriteGameDAOBean;
    }

    // 새 게임 생성 : 게임Id 생성과 조회 및 16개 컨텐츠 조회
    public ResponseFavoriteGameDTO getGame(UUID userId){

        // FavoriteGame DAO 생성 -> gameId, userId, favoriteList 값 초기화
        FavoriteGame favoriteGame = new FavoriteGame();
        favoriteGame.setGameId(UUID.randomUUID());
        favoriteGame.setUserId(userId);

        // 16개 컨텐츠 랜덤 선택 // 일단 2개로...
        favoriteGame.setFavoriteList(getRandomFavoriteDAOsBean.exec());

        // DAO 저장
        saveFavoriteGameDAOBean.exec(favoriteGame);

        // ResponseFavoriteGameDTO 생성
        ResponseFavoriteGameDTO responseFavoriteGameDTO = new ResponseFavoriteGameDTO();

        // gameId와 favoriteList 값 설정
        responseFavoriteGameDTO.setGameId(favoriteGame.getGameId());
        responseFavoriteGameDTO.setFavoriteList(favoriteGame.getFavoriteList());

        // 생성된 ResponseFavoriteGameDTO 반환
        return responseFavoriteGameDTO;
    }
}
