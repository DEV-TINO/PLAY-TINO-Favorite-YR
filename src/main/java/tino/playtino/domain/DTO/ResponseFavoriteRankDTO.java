package tino.playtino.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseFavoriteRankDTO {
    UUID favoriteId;
    String favoriteImage;
    String favoriteTitle;

    Integer favoriteRankCount;
    Double favoriteRankPercentage;
}
