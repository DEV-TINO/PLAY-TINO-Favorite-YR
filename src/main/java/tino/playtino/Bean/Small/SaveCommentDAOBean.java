package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.others.CheckValidCommentBean;
import tino.playtino.domain.Comment;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.repository.JpaCommentRepository;

@Component
public class SaveCommentDAOBean {

    JpaCommentRepository jpaCommentRepository;
    CheckValidCommentBean checkValidCommentBean;

    @Autowired
    public SaveCommentDAOBean(JpaCommentRepository jpaCommentRepository, CheckValidCommentBean checkValidCommentBean){
        this.jpaCommentRepository = jpaCommentRepository;
        this.checkValidCommentBean = checkValidCommentBean;
    }

    // 댓글 저장
    public ResponseSuccess exec(Comment comment){

        //responseSuccess 생성
        ResponseSuccess responseSuccess = new ResponseSuccess();

        //comment의 값들이 유효한지 검사해서, 결과에 따라 '성공 여부'를 설정
        responseSuccess.setSuccess(checkValidCommentBean.exec(comment));

        //성공한 경우만 DAO 저장
        if(responseSuccess.getSuccess()) jpaCommentRepository.save(comment);

        //'성공 여부'가 설정된 responseSuccess 반환
        return responseSuccess;
    }
}
