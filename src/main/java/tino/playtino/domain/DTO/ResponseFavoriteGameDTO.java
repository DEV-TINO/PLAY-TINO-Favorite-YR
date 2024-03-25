package tino.playtino.domain.DTO;

import jakarta.persistence.Convert;
import lombok.Data;
import tino.playtino.domain.FavoriteDAO;
import tino.playtino.others.StringListConverter;

import java.util.List;
import java.util.UUID;

@Data
public class ResponseFavoriteGameDTO {
    UUID gameId;

    @Convert(converter = StringListConverter.class)
    List<FavoriteDAO> favoriteDAOList;
}
