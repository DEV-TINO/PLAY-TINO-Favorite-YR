package tino.playtino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tino.playtino.domain.FavoriteRankDAO;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaFavoriteRankRepository extends JpaRepository<FavoriteRankDAO, UUID> {

    // favoriteId로 favoriteRankList 찾기
    List<FavoriteRankDAO> findByFavoriteId(UUID favoriteId);
}
