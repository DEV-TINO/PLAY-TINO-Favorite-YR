package tino.playtino.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import tino.playtino.domain.FavoriteCountDAO;

import java.util.UUID;

public interface JpaFavoriteCountRepository extends JpaRepository<FavoriteCountDAO, UUID> {

    // 페이징해서 FavoriteCount 찾기
    Page<FavoriteCountDAO> findAll(Pageable pageable);
}
