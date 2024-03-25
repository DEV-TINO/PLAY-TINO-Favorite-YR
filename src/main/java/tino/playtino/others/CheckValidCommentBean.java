package tino.playtino.others;

import org.springframework.stereotype.Component;
import tino.playtino.domain.CommentDAO;

@Component
public class CheckValidCommentBean {

    //Comment DAO 값이 유효한지 [null이 없는지] 확인하는 기능
    public Boolean exec(CommentDAO commentDAO){

        //DAO 값 모두 null이 아니라면 true 반환
        if(
                commentDAO.getCommentId() != null
                && commentDAO.getUserId() != null
                && commentDAO.getContent() != null
                && commentDAO.getHeartCount() != null
                && commentDAO.getUploadTime() != null
        ){
            return true;
        }

        //null이 하나라도 있다면 false 반환
        else return false;
    }
}
