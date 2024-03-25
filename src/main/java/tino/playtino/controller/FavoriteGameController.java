package tino.playtino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tino.playtino.domain.DTO.ResponseFavoriteGameDTO;
import tino.playtino.service.FavoriteGameService;

import java.util.UUID;

@RestController
@CrossOrigin("*")
public class FavoriteGameController {

    FavoriteGameService favoriteGameService;

    @Autowired
    public FavoriteGameController(FavoriteGameService favoriteGameService){
        this.favoriteGameService = favoriteGameService;
    }

    // 게임 시작 [컨텐츠 16개 조회]
    @GetMapping("/favorite/all/{userId}")
    public ResponseFavoriteGameDTO createGame(@PathVariable UUID userId, @RequestParam(required = false) UUID gameId){

        return favoriteGameService.createGame(userId, gameId);
    }
}
