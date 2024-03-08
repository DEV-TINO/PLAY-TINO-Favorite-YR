package tino.playtino.Bean.Small;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tino.playtino.repository.JpaCommentHeartRepository;

@Component
public class GetCommentHeartDAOBean {

    JpaCommentHeartRepository jpaCommentHeartRepository;

    @Autowired
    public GetCommentHeartDAOBean(JpaCommentHeartRepository jpaCommentHeartRepository){
        this.jpaCommentHeartRepository = jpaCommentHeartRepository;
    }
}
