package tino.playtino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tino.playtino.domain.Favorite;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaFavoriteRepository extends JpaRepository<Favorite, UUID> {

    // 레코드 랜덤 검색
    @Query(value = "SELECT favorite FROM Favorite favorite order by RAND() limit 2")
    public List<Favorite> findFavorites();
}
