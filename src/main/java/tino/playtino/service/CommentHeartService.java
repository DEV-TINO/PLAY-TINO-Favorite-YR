package tino.playtino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tino.playtino.Bean.Small.*;
import tino.playtino.domain.*;
import tino.playtino.domain.DTO.RequestCommentHeartDeleteDTO;
import tino.playtino.domain.DTO.RequestCommentHeartSaveDTO;

import java.util.UUID;

@Service
public class CommentHeartService {

    GetCommentDAOBean getCommentDAOBean;

    UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    SaveCommentHeartDAOBean saveCommentHeartDAOBean;
    DeleteCommentHeartDAOBean deleteCommentHeartDAOBean;
    GetCommentHeartDAOBean getCommentHeartDAOBean;

    @Autowired
    public CommentHeartService(UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean, SaveCommentDAOBean saveCommentDAOBean, SaveCommentHeartDAOBean saveCommentHeartDAOBean, DeleteCommentHeartDAOBean deleteCommentHeartDAOBean, GetCommentHeartDAOBean getCommentHeartDAOBean){
        this.updateCommentHeartCountDAOBean = updateCommentHeartCountDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.saveCommentHeartDAOBean = saveCommentHeartDAOBean;
        this.deleteCommentHeartDAOBean = deleteCommentHeartDAOBean;
        this.getCommentHeartDAOBean = getCommentHeartDAOBean;
    }

    //댓글좋아요 저장
    public ResponseSuccess createHeart(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO){

        //commentHeartSaveBean.exec();
        // 기존에 있는 좋아요인지 중복확인
        UUID commentId = requestCommentHeartSaveDTO.getCommentId();
        UUID userId = requestCommentHeartSaveDTO.getUserId();

        if(getCommentHeartDAOBean.exec(commentId, userId) != null){
            ResponseSuccess responseSuccess = new ResponseSuccess();
            responseSuccess.setSuccess(false);
            return responseSuccess;
        }

        // 저장할 댓글좋아요 DAO 생성 및 값 초기화
        CommentHeartDAO commentHeartDAO = new CommentHeartDAO();
        commentHeartDAO.setCommentHeartId(UUID.randomUUID());
        commentHeartDAO.setCommentId(requestCommentHeartSaveDTO.getCommentId());
        commentHeartDAO.setUserId(requestCommentHeartSaveDTO.getUserId());

        // 댓글 좋아요 생성으로 인한 댓글 좋아요 갯수 증가(해당 댓글 DAO를 업데이트)
        CommentDAO commentDAO = getCommentDAOBean.exec(requestCommentHeartSaveDTO.getCommentId());
        updateCommentHeartCountDAOBean.heartCountUp(commentDAO);

        // 좋아요 수가 변경된 댓글 DAO 저장[업데이트]
        saveCommentDAOBean.exec(commentDAO);

        // 댓글좋아요 DAO 저장
        return saveCommentHeartDAOBean.exec(commentHeartDAO);
    }

    //댓글좋아요 삭제
    public ResponseSuccess deleteHeart(RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO){

        //userId와 commentId로 댓글좋아요(DAO) 찾기
        CommentHeartDAO commentHeartDAO
                = getCommentHeartDAOBean.exec(requestCommentHeartDeleteDTO.getCommentId(), requestCommentHeartDeleteDTO.getUserId());

        // 해당하는 댓글 좋아요가 있는지 확인 (없으면 '실패' 반환)
        if(commentHeartDAO == null){
            ResponseSuccess responseSuccess = new ResponseSuccess();
            responseSuccess.setSuccess(false);
            return responseSuccess;
        }

        // 댓글 좋아요 삭제로 인한 댓글 좋아요 개수 감소(해당 댓글 DAO를 업데이트)
        CommentDAO commentDAO = getCommentDAOBean.exec(requestCommentHeartDeleteDTO.getCommentId());
        updateCommentHeartCountDAOBean.heartCountDown(commentDAO);

        // 좋아요 수가 변경된 댓글 DAO 저장[업데이트]
        saveCommentDAOBean.exec(commentDAO);

        // 댓글좋아요 DAO 삭제
        //jpaCommentHeartRepository.delete(commentHeart);
        return deleteCommentHeartDAOBean.exec(commentHeartDAO);
    }
}
