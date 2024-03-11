package tino.playtino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tino.playtino.domain.FavoriteRank;

import java.util.UUID;

@Repository
public interface JpaFavoriteRankRepository extends JpaRepository<FavoriteRank, UUID> {
}
