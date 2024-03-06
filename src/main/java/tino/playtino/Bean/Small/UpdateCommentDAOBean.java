package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.Bean.CheckUserCommentBean;
import tino.playtino.domain.Comment;
import tino.playtino.domain.RequestCommentUpdateDTO;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.repository.JpaCommentRepository;

import java.util.UUID;

@Component
public class UpdateCommentDAOBean {
    JpaCommentRepository jpaCommentRepository;
    CheckUserCommentBean checkUserCommentBean;

    @Autowired
    public UpdateCommentDAOBean(JpaCommentRepository jpaCommentRepository, CheckUserCommentBean checkUserCommentBean){
        this.jpaCommentRepository= jpaCommentRepository;
        this.checkUserCommentBean = checkUserCommentBean;
    }

    //댓글 수정
    public ResponseSuccess exec(Comment comment, RequestCommentUpdateDTO requestCommentUpdateDTO){
        //responseSuccess 생성
        ResponseSuccess responseSuccess = new ResponseSuccess();

        //댓글이 유저가 작성한 댓글이 맞는지 확인 -> 결과에 따라 '성공 여부' 설정
        if(checkUserCommentBean.exec(comment, requestCommentUpdateDTO.getUserId()))
            responseSuccess.setSuccess(true);
        else responseSuccess.setSuccess(false);

        //성공한 경우만 DAO 수정해서 저장
        if((responseSuccess.getSuccess())){

            comment.setContent(requestCommentUpdateDTO.getContent());
            jpaCommentRepository.save(comment);
        }

        //'성공 여부'가 설정된 responseSuccess 반환
        return responseSuccess;
    }
}
