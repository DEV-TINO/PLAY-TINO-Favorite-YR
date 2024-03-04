package tino.playtino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tino.playtino.domain.CommentHeart;

import java.util.UUID;

@Repository
public interface JpaCommentHeartRepository extends JpaRepository<CommentHeart, UUID> {

    //commentId, userId로 CommentHeart 찾기
    public CommentHeart findByCommentIdAndUserId(UUID commentId, UUID userId);
}
