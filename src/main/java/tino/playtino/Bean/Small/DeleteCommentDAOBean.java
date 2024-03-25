package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.others.CheckUserCommentBean;
import tino.playtino.domain.CommentDAO;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.repository.JpaCommentRepository;

import java.util.UUID;

@Component
public class DeleteCommentDAOBean {

    JpaCommentRepository jpaCommentRepository;
    CheckUserCommentBean checkUserCommentBean;

    @Autowired
    public DeleteCommentDAOBean(JpaCommentRepository jpaCommentRepository, CheckUserCommentBean checkUserCommentBean){
        this.jpaCommentRepository= jpaCommentRepository;
        this.checkUserCommentBean = checkUserCommentBean;
    }

    //댓글 삭제
    public ResponseSuccess exec(CommentDAO commentDAO, UUID userId){
        //responseSuccess 생성
        ResponseSuccess responseSuccess = new ResponseSuccess();

        //댓글이 유저가 작성한 댓글이 맞는지 확인 -> 결과에 따라 '성공 여부' 설정
        if(checkUserCommentBean.exec(commentDAO, userId)) responseSuccess.setSuccess(true);
        else responseSuccess.setSuccess(false);

        //성공한 경우만 DAO 삭제
        if((responseSuccess.getSuccess())) jpaCommentRepository.delete(commentDAO);

        //'성공 여부'가 설정된 responseSuccess 반환
        return responseSuccess;
    }
}
