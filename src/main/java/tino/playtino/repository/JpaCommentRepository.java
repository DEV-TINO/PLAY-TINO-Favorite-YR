package tino.playtino.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tino.playtino.domain.Comment;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaCommentRepository extends JpaRepository<Comment, UUID> {

    //정렬해서 전체 comment 찾기(기준 : uploadTime 내림차순[최신순])
    List<Comment> findAllByOrderByUploadTimeDesc();

    //정렬해서 전체 comment 찾기(기준 : heartCount 내림차순[최신순])
    List<Comment> findAllByOrderByHeartCountDescUploadTimeDesc();

    // 페이징해서 comment 찾기
    Page<Comment> findAll(Pageable pageable);
}
