package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.CommentHeartDAO;
import tino.playtino.repository.JpaCommentHeartRepository;

import java.util.UUID;

@Component
public class GetCommentHeartDAOBean {

    JpaCommentHeartRepository jpaCommentHeartRepository;

    @Autowired
    public GetCommentHeartDAOBean(JpaCommentHeartRepository jpaCommentHeartRepository){
        this.jpaCommentHeartRepository = jpaCommentHeartRepository;
    }

    public CommentHeartDAO exec(UUID commentId, UUID userId){
        return jpaCommentHeartRepository.findByCommentIdAndUserId(commentId, userId);
    }
}
