package tino.playtino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tino.playtino.domain.DTO.RequestCommentHeartDeleteDTO;
import tino.playtino.domain.DTO.RequestCommentHeartSaveDTO;
import tino.playtino.domain.ResponseSuccess;
import tino.playtino.service.CommentHeartService;

@RestController
public class CommentHeartController {

    private final CommentHeartService commentHeartService;

    @Autowired
    public CommentHeartController(CommentHeartService commentHeartService){
        this.commentHeartService = commentHeartService;
    }

    //댓글좋아요 저장
    @PostMapping("/comment-heart")
    public ResponseSuccess create(@RequestBody RequestCommentHeartSaveDTO requestCommentHeartSaveDTO){

        return commentHeartService.insert(requestCommentHeartSaveDTO);
    }

    //댓글좋아요 삭제
    @DeleteMapping("/comment-heart")
    public ResponseSuccess delete(@RequestBody RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO){

        return commentHeartService.delete(requestCommentHeartDeleteDTO);
    }
}
