package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.Comment;
import tino.playtino.repository.JpaCommentRepository;

import java.util.UUID;

@Component
public class GetCommentDAOBean {

    JpaCommentRepository jpaCommentRepository;

    @Autowired
    public GetCommentDAOBean(JpaCommentRepository jpaCommentRepository){
        this.jpaCommentRepository = jpaCommentRepository;
    }

    // commentId에 해당하는 댓글을 찾아서 반환하는 메서드
    public Comment exec(UUID commentId){

        return jpaCommentRepository.findById(commentId).get();
    }
}
