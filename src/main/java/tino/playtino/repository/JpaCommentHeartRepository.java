package tino.playtino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tino.playtino.domain.CommentHeartDAO;

import java.util.UUID;

@Repository
public interface JpaCommentHeartRepository extends JpaRepository<CommentHeartDAO, UUID> {

    //commentId, userId로 CommentHeart 찾기
    public CommentHeartDAO findByCommentIdAndUserId(UUID commentId, UUID userId);
}
