package tino.playtino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tino.playtino.domain.DTO.RequestCommentHeartDeleteDTO;
import tino.playtino.domain.DTO.RequestCommentHeartSaveDTO;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.service.CommentHeartService;

@RestController
@CrossOrigin("*")
public class CommentHeartController {

    private final CommentHeartService commentHeartService;

    @Autowired
    public CommentHeartController(CommentHeartService commentHeartService){
        this.commentHeartService = commentHeartService;
    }

    //댓글좋아요 저장
    @PostMapping("/comment-heart")
    public ResponseSuccess createHeart(@RequestBody RequestCommentHeartSaveDTO requestCommentHeartSaveDTO){

        return commentHeartService.createHeart(requestCommentHeartSaveDTO);
    }

    //댓글좋아요 삭제
    @DeleteMapping("/comment-heart")
    public ResponseSuccess deleteHeart(@RequestBody RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO){

        return commentHeartService.deleteHeart(requestCommentHeartDeleteDTO);
    }
}
