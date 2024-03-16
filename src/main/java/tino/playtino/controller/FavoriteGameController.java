package tino.playtino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tino.playtino.domain.DTO.ResponseFavoriteGameDTO;
import tino.playtino.service.FavoriteGameService;

import java.util.UUID;

@RestController
public class FavoriteGameController {

    FavoriteGameService favoriteGameService;

    @Autowired
    public FavoriteGameController(FavoriteGameService favoriteGameService){
        this.favoriteGameService = favoriteGameService;
    }

    // 게임 시작 [컨텐츠 16개 조회]
    @GetMapping("/favorite/all/{userId}")
    public ResponseFavoriteGameDTO startGame(@PathVariable UUID userId, @RequestParam(required = false) UUID gameId){

        return favoriteGameService.getGame(userId, gameId);
    }
}
