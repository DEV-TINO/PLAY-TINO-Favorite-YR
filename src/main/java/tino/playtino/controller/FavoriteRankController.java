package tino.playtino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tino.playtino.domain.DTO.RequestFavoriteRankDTO;
import tino.playtino.domain.DTO.ResponseFavoriteRanksDTO;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.service.FavoriteRankService;

@RestController
public class FavoriteRankController {

    FavoriteRankService favoriteRankService;

    @Autowired
    public FavoriteRankController(FavoriteRankService favoriteRankService){
        this.favoriteRankService = favoriteRankService;
    }

    // 랭킹 저장
    @PostMapping("/favorite/rank")
    public ResponseSuccess saveRank(@RequestBody RequestFavoriteRankDTO requestFavoriteRankDTO){

        return favoriteRankService.saveRank(requestFavoriteRankDTO);
    }

    // 랭킹 전체 조회
    @GetMapping("/favorite/rank/all")
    public ResponseFavoriteRanksDTO readAll(){
        return favoriteRankService.readAll();
    }

}
