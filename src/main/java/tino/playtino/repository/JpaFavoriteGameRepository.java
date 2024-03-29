package tino.playtino.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tino.playtino.domain.FavoriteGameDAO;

import java.util.UUID;

@Repository
public interface JpaFavoriteGameRepository extends JpaRepository<FavoriteGameDAO, UUID> {

}
