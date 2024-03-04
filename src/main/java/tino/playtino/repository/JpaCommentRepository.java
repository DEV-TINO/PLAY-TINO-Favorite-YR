package tino.playtino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tino.playtino.domain.Comment;

import java.util.UUID;

@Repository
public interface JpaCommentRepository extends JpaRepository<Comment, UUID> {
}
