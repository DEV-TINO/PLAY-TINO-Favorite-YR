package tino.playtino.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tino.playtino.Bean.SaveCommentDAOBean;
import tino.playtino.Bean.UpdateCommentHeartCountDAOBean;
import tino.playtino.domain.Comment;
import tino.playtino.domain.CommentHeart;
import tino.playtino.domain.RequestCommentHeartDeleteDTO;
import tino.playtino.domain.RequestCommentHeartSaveDTO;
import tino.playtino.repository.JpaCommentHeartRepository;

import java.util.UUID;

@Service
public class CommentHeartService {

    JpaCommentHeartRepository jpaCommentHeartRepository;
    UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;

    @Autowired
    public CommentHeartService(JpaCommentHeartRepository jpaCommentHeartRepository, UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean, SaveCommentDAOBean saveCommentDAOBean){
        this.jpaCommentHeartRepository = jpaCommentHeartRepository;
        this.updateCommentHeartCountDAOBean = updateCommentHeartCountDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
    }

    //댓글좋아요 저장
    public UUID insert(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO){

        //이미 존재하면 null 반환
        // 기존에 있는 좋아요인지 중복확인
        UUID commentId = requestCommentHeartSaveDTO.getCommentId();
        UUID userId = requestCommentHeartSaveDTO.getUserId();
        if(jpaCommentHeartRepository.findByCommentIdAndUserId(commentId, userId) != null)
            return null;


        //DAO 생성 -> PK값(UUID) 설정
        // 저장할 댓글 DAO 생성 및 값 초기화
        CommentHeart commentHeart = new CommentHeart();
        commentHeart.setCommentHeartId(UUID.randomUUID());

        //DTO의 userId, commentId 값을 DAO에 넣어줌
        commentHeart.setCommentId(requestCommentHeartSaveDTO.getCommentId());
        commentHeart.setUserId(requestCommentHeartSaveDTO.getUserId());


        //생성한 하트의 commentId에 해당하는 Comment의 하트 개수 증가(+1)
        //C~H~Service에 C~S~ 주입, CS에 heartCountUp(UUID commentId) 구현, CommentHeart insert 시 heartCountUp 호출
        // 댓글 좋아요 생성으로 인한 댓글 좋아요 갯수 증가
        Comment comment = updateCommentHeartCountDAOBean.heartCountUp(requestCommentHeartSaveDTO.getCommentId());

        // 댓글 DAO 저장
        saveCommentDAOBean.exec(comment);

        //DAO 저장
        jpaCommentHeartRepository.save(commentHeart);

        //저장한 DAO의 PK값 반환
        return commentHeart.getCommentHeartId();
    }

    //댓글좋아요 삭제
    public UUID delete(RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO){
        //userId, commentId로 commentHeart 찾는 findByCommentIdAndUserId(...) 호출해서 DAO 찾기
        UUID commentId = requestCommentHeartDeleteDTO.getCommentId();
        UUID userId = requestCommentHeartDeleteDTO.getUserId();
        CommentHeart commentHeart
                = jpaCommentHeartRepository.findByCommentIdAndUserId(commentId, userId);

        //삭제한 하트의 commentId에 해당하는 Comment의 하트 개수 감소(-1)
        //좋아요개수 -1 메서드 호출
        Comment comment = updateCommentHeartCountDAOBean.heartCountDown(commentHeart.getCommentId());

        // 댓글 DAO 저장
        saveCommentDAOBean.exec(comment);

        //해당 CommentHeart(DAO) 삭제
        jpaCommentHeartRepository.delete(commentHeart);

        //삭제한 DAO의 PK(CommentHeartId) 반환
        return commentHeart.getCommentHeartId();
    }
}
