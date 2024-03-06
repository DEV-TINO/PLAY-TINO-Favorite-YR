package tino.playtino.Bean;

import org.springframework.stereotype.Component;
import tino.playtino.domain.Comment;
import tino.playtino.domain.ResponseSuccess;

@Component
public class CheckValidCommentBean {

    //Comment DAO 값이 유효한지 [null이 없는지] 확인하는 기능
    public Boolean exec(Comment comment){

        //DAO 값 모두 null이 아니라면 true 반환
        if(
                comment.getCommentId() != null
                && comment.getUserId() != null
                && comment.getContent() != null
                && comment.getHeartCount() != null
                && comment.getUploadTime() != null
        ){
            return true;
        }

        //null이 하나라도 있다면 false 반환
        else return false;
    }
}
