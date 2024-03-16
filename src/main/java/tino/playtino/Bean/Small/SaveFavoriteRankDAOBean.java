package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.others.CheckValidFavoriteRankBean;
import tino.playtino.domain.FavoriteRank;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.repository.JpaFavoriteRankRepository;

@Component
public class SaveFavoriteRankDAOBean {

    JpaFavoriteRankRepository favoriteRankRepository;
    CheckValidFavoriteRankBean checkValidFavoriteRankBean;

    @Autowired
    public SaveFavoriteRankDAOBean(JpaFavoriteRankRepository favoriteRankRepository, CheckValidFavoriteRankBean checkValidFavoriteRankBean){
        this.favoriteRankRepository = favoriteRankRepository;
        this.checkValidFavoriteRankBean = checkValidFavoriteRankBean;
    }

    // FavoriteRank 저장, Success(성공 여부) 반환
    public ResponseSuccess exec(FavoriteRank favoriteRank){
        //responseSuccess 생성
        ResponseSuccess responseSuccess = new ResponseSuccess();

        //favoriteRank의 값들이 유효한지 검사해서, 결과에 따라 '성공 여부'를 설정
        responseSuccess.setSuccess(checkValidFavoriteRankBean.exec(favoriteRank));

        //성공한 경우만 DAO 저장
        if(responseSuccess.getSuccess()) favoriteRankRepository.save(favoriteRank);

        //'성공 여부'가 설정된 responseSuccess 반환
        return responseSuccess;
    }
}
