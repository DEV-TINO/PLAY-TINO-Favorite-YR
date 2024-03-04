package tino.playtino.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.Comment;
import tino.playtino.repository.JpaCommentRepository;

@Component
public class SaveCommentDAOBean {

    JpaCommentRepository jpaCommentRepository;

    @Autowired
    public SaveCommentDAOBean(JpaCommentRepository jpaCommentRepository){
        this.jpaCommentRepository = jpaCommentRepository;
    }

    // 댓글 저장
    public void exec(Comment comment){
        jpaCommentRepository.save(comment);
    }
}
