package tino.playtino.Bean;

import org.springframework.stereotype.Component;
import tino.playtino.domain.Comment;
import tino.playtino.domain.CommentHeart;

@Component
public class CheckValidCommentHeartBean {

    //CommentHeart DAO 값이 유효한지 [null이 없는지] 확인하는 기능
    public Boolean exec(CommentHeart commentHeart){

        //DAO 값 모두 null이 아니라면 true 반환
        if(
                commentHeart.getCommentHeartId() != null
                && commentHeart.getCommentId() != null
                && commentHeart.getUserId() != null
        ){
            return true;
        }

        //null이 하나라도 있다면 false 반환
        else return false;
    }
}