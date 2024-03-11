package tino.playtino.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tino.playtino.domain.DTO.RequestFavoriteRankDTO;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.service.FavoriteRankService;

@RestController
public class FavoriteRankController {

    FavoriteRankService favoriteRankService;
    public FavoriteRankController(FavoriteRankService favoriteRankService){
        this.favoriteRankService = favoriteRankService;
    }

    // 랭킹 저장
    @PostMapping("/favorite/rank")
    public ResponseSuccess saveRank(@RequestBody RequestFavoriteRankDTO requestFavoriteRankDTO){

        return favoriteRankService.saveRank(requestFavoriteRankDTO);
    }
}
