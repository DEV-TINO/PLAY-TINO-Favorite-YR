package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.CommentHeart;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.repository.JpaCommentHeartRepository;

@Component
public class DeleteCommentHeartDAOBean {

    JpaCommentHeartRepository jpaCommentHeartRepository;

    @Autowired
    public DeleteCommentHeartDAOBean(JpaCommentHeartRepository jpaCommentHeartRepository){
        this.jpaCommentHeartRepository = jpaCommentHeartRepository;
    }

    // 댓글좋아요 삭제
    public ResponseSuccess exec(CommentHeart commentHeart){

        //responseSuccess 생성
        ResponseSuccess responseSuccess = new ResponseSuccess();

        //'성공 여부'를 true로 설정
        responseSuccess.setSuccess(true);

        //성공한 경우만 댓글좋아요(DAO) 삭제
        jpaCommentHeartRepository.delete(commentHeart);

        //'성공 여부'가 설정된 responseSuccess 반환
        return responseSuccess;
    }
}
