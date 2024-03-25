package tino.playtino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tino.playtino.Bean.Small.GetFavoriteGameDAOBean;
import tino.playtino.Bean.Small.GetRandomFavoriteDAOsBean;
import tino.playtino.Bean.Small.SaveFavoriteGameDAOBean;
import tino.playtino.domain.FavoriteGameDAO;
import tino.playtino.domain.DTO.ResponseFavoriteGameDTO;
import tino.playtino.repository.JpaFavoriteGameRepository;

import java.util.UUID;

@Service
public class FavoriteGameService {

    JpaFavoriteGameRepository jpaFavoriteGameRepository;
    GetRandomFavoriteDAOsBean getRandomFavoriteDAOsBean;
    SaveFavoriteGameDAOBean saveFavoriteGameDAOBean;
    GetFavoriteGameDAOBean getFavoriteGameDAOBean;

    @Autowired
    public FavoriteGameService(JpaFavoriteGameRepository jpaFavoriteGameRepository, GetRandomFavoriteDAOsBean getRandomFavoriteDAOsBean, SaveFavoriteGameDAOBean saveFavoriteGameDAOBean, GetFavoriteGameDAOBean getFavoriteGameDAOBean){
        this.jpaFavoriteGameRepository = jpaFavoriteGameRepository;
        this.getRandomFavoriteDAOsBean = getRandomFavoriteDAOsBean;
        this.saveFavoriteGameDAOBean = saveFavoriteGameDAOBean;
        this.getFavoriteGameDAOBean = getFavoriteGameDAOBean;
    }

    // 새 게임 생성 : 게임Id 생성과 조회 및 16개 컨텐츠 조회
    public ResponseFavoriteGameDTO createGame(UUID userId, UUID gameId){

        // gameId 가 없을경우 있을경우를 나눈다
        
        // gameId가 있을 경우 : 기존에 있던 게임을 찾아서 반환
        if(gameId != null){
            
            // favoriteGame (DAO) 찾기 : getFavoriteGameDAOBean
            FavoriteGameDAO favoriteGameDAO = getFavoriteGameDAOBean.exec(gameId);
            
            // 반환할 ResponseFavoriteGameDTO 생성
            ResponseFavoriteGameDTO responseFavoriteGameDTO = new ResponseFavoriteGameDTO();
            
            // DTO 값 설정
            responseFavoriteGameDTO.setGameId(gameId);
            responseFavoriteGameDTO.setFavoriteDAOList(favoriteGameDAO.getFavoriteDAOList());

            // DTO 반환
            return  responseFavoriteGameDTO;
        }

        // gameId가 없을 경우
        
        // FavoriteGame DAO 생성 -> gameId, userId, favoriteList 값 초기화
        FavoriteGameDAO favoriteGameDAO = new FavoriteGameDAO();
        favoriteGameDAO.setGameId(UUID.randomUUID());
        favoriteGameDAO.setUserId(userId);

        // 16개 컨텐츠 랜덤 선택 // 일단 2개로...
        favoriteGameDAO.setFavoriteDAOList(getRandomFavoriteDAOsBean.exec());

        // DAO 저장
        saveFavoriteGameDAOBean.exec(favoriteGameDAO);

        // ResponseFavoriteGameDTO 생성
        ResponseFavoriteGameDTO responseFavoriteGameDTO = new ResponseFavoriteGameDTO();

        // gameId와 favoriteList 값 설정
        responseFavoriteGameDTO.setGameId(favoriteGameDAO.getGameId());
        responseFavoriteGameDTO.setFavoriteDAOList(favoriteGameDAO.getFavoriteDAOList());

        // 생성된 ResponseFavoriteGameDTO 반환
        return responseFavoriteGameDTO;
    }
}
