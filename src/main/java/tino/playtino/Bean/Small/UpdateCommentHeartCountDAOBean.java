package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.CommentDAO;

@Component
public class UpdateCommentHeartCountDAOBean {

    GetCommentDAOBean getCommentDAOBean;

    @Autowired
    public UpdateCommentHeartCountDAOBean(GetCommentDAOBean getCommentDAOBean){
        this.getCommentDAOBean = getCommentDAOBean;
    }

    //댓글좋아요 추가(+1)
    public void heartCountUp(CommentDAO commentDAO){
        //DAO의 댓글좋아요개수(HeartCount) +1
        commentDAO.setHeartCount(commentDAO.getHeartCount()+1);
    }

    //댓글좋아요 삭제(-1)
    public void heartCountDown(CommentDAO commentDAO){
        //DAO의 댓글좋아요개수(HeartCount) -1
        commentDAO.setHeartCount(commentDAO.getHeartCount()-1);
    }
}
