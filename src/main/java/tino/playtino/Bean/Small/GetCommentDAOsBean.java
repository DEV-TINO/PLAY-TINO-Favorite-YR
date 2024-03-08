package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.domain.Comment;
import tino.playtino.repository.JpaCommentRepository;

import java.util.List;

@Component
public class GetCommentDAOsBean {
    
    JpaCommentRepository jpaCommentRepository;
    
    @Autowired
    public GetCommentDAOsBean(JpaCommentRepository jpaCommentRepository){
        this.jpaCommentRepository = jpaCommentRepository;
    }
    
    // 모든 댓글을 찾아서 List로 반환하는 메서드
    public List<Comment> exec(){
        
        return jpaCommentRepository.findAll();
    }

    // 모든 댓글을 uploadTime을 기준으로 정렬 검색해 List로 반환하는 메서드
    public List<Comment> execByTime(){

        return jpaCommentRepository.findAllByOrderByUploadTimeDesc();
    }

    // 모든 댓글을 heartCount를 기준으로 정렬 검색해 List로 반환하는 메서드
    public List<Comment> execByHeart(){

        return jpaCommentRepository.findAllByOrderByHeartCountDesc();
    }
}
