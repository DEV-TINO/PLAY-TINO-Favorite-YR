package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.CommentHeart;
import tino.playtino.repository.JpaCommentHeartRepository;

import java.util.UUID;

@Component
public class GetBooleanUserHeartExistsBean {

    JpaCommentHeartRepository jpaCommentHeartRepository;

    @Autowired
    public GetBooleanUserHeartExistsBean(JpaCommentHeartRepository jpaCommentHeartRepository){
        this.jpaCommentHeartRepository = jpaCommentHeartRepository;
    }

    //댓글에 대한 유저의 하트가 존재하는지 체크
    public Boolean checkUserHeartExists(UUID commentId, UUID userId){

        //유저가 댓글에 누른 하트를 찾음
        CommentHeart commentHeart = jpaCommentHeartRepository.findByCommentIdAndUserId(commentId, userId);

        //해당하는 하트가 있으면 true, 없으면 false 반환
        //return commentHeart.getCommentHeartId() != null;
        return commentHeart != null;
    }
}
