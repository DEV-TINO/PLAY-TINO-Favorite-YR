package tino.playtino.domain.DTO;

import jakarta.persistence.Convert;
import lombok.Data;
import tino.playtino.others.StringListConverter;

import java.util.List;

@Data
public class ResponseFavoriteRanksDTO {
    Integer favoriteRankTotal;  // 완료된 전체 게임 수

    @Convert(converter = StringListConverter.class)
    List<ResponseFavoriteRankDTO> favoriteRanks;    // 이긴 횟수(favoriteRankCount)를 포함하는 Favorite 리스트

}
