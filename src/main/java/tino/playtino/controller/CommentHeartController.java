package tino.playtino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tino.playtino.domain.RequestCommentHeartDeleteDTO;
import tino.playtino.domain.RequestCommentHeartSaveDTO;
import tino.playtino.service.CommentHeartService;

import java.util.UUID;

@RestController
public class CommentHeartController {

    private final CommentHeartService commentHeartService;

    @Autowired
    public CommentHeartController(CommentHeartService commentHeartService){
        this.commentHeartService = commentHeartService;
    }

    //댓글좋아요 저장
    @PostMapping("/comment-heart")
    public UUID create(@RequestBody RequestCommentHeartSaveDTO requestCommentHeartSaveDTO){

        //저장 -> 저장한 CommentHeart의 PK 반환
        return commentHeartService.insert(requestCommentHeartSaveDTO);
    }

    //댓글좋아요 삭제
    @DeleteMapping("/comment-heart")
    public UUID delete(@RequestBody RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO){

        //삭제 -> 삭제한 CommentHeart의 PK 반환
        return commentHeartService.delete(requestCommentHeartDeleteDTO);
    }
}
