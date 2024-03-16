package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.Comment;

import java.util.UUID;

@Component
public class UpdateCommentHeartCountDAOBean {

    GetCommentDAOBean getCommentDAOBean;

    @Autowired
    public UpdateCommentHeartCountDAOBean(GetCommentDAOBean getCommentDAOBean){
        this.getCommentDAOBean = getCommentDAOBean;
    }

    //댓글좋아요 추가(+1)
    public Comment heartCountUp(UUID commentId){

        //commentId로 해당 Comment(DAO) 찾기
        Comment comment = getCommentDAOBean.exec(commentId);

        //DAO의 댓글좋아요개수(HeartCount) +1
        comment.setHeartCount(comment.getHeartCount()+1);

        return comment;

    }

    //댓글좋아요 삭제(-1)
    public Comment heartCountDown(UUID commentId){

        //commentId로 해당 Comment(DAO) 찾기
        Comment comment = getCommentDAOBean.exec(commentId);

        //댓글좋아요개수(HeartCount) -1
        comment.setHeartCount(comment.getHeartCount()-1);

        return comment;

    }
}
