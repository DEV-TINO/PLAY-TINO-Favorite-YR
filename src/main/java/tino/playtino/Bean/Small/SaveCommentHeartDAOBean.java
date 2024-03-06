package tino.playtino.Bean.Small;

import org.springframework.stereotype.Component;
import tino.playtino.Bean.CheckValidCommentHeartBean;
import tino.playtino.domain.CommentHeart;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.repository.JpaCommentHeartRepository;

@Component
public class SaveCommentHeartDAOBean {

    JpaCommentHeartRepository jpaCommentHeartRepository;
    CheckValidCommentHeartBean checkValidCommentHeartBean;

    public SaveCommentHeartDAOBean(JpaCommentHeartRepository jpaCommentHeartRepository, CheckValidCommentHeartBean checkValidCommentHeartBean){
        this.jpaCommentHeartRepository = jpaCommentHeartRepository;
        this.checkValidCommentHeartBean = checkValidCommentHeartBean;
    }

    //댓글좋아요 저장
    public ResponseSuccess exec(CommentHeart commentHeart){
        //responseSuccess 생성
        ResponseSuccess responseSuccess = new ResponseSuccess();

        //commentHeart의 값들이 유효한지 검사해서, 결과에 따라 '성공 여부'를 설정
        responseSuccess.setSuccess(checkValidCommentHeartBean.exec(commentHeart));

        //성공한 경우만 DAO 저장
        if(responseSuccess.getSuccess()) jpaCommentHeartRepository.save(commentHeart);

        //'성공 여부'가 설정된 responseSuccess 반환
        return responseSuccess;
    }
}
