package tino.playtino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tino.playtino.domain.*;
import tino.playtino.service.CommentService;

import java.util.List;
import java.util.UUID;

@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //댓글 조회
    @GetMapping("/comment/{commentId}")
    public ResponseCommentDTO read(@PathVariable UUID commentId){

        return commentService.read(commentId);
    }

    //댓글 전체 조회
    @GetMapping("/comment/all")
    public List<ResponseCommentDTO> readAll(){

        return commentService.readAll();
    }

    //댓글 저장
    @PostMapping("/comment")
    public ResponseSuccess create(@RequestBody RequestCommentSaveDTO requestCommentSaveDTO){

        return commentService.insert(requestCommentSaveDTO);
    }

    //댓글 수정
    @PutMapping("/comment")
    public ResponseSuccess update(@RequestBody RequestCommentUpdateDTO requestCommentUpdateDTO){

        return commentService.update(requestCommentUpdateDTO);
    }

    //댓글 삭제
    @DeleteMapping("/comment")
    public ResponseSuccess delete(@RequestBody RequestCommentDeleteDTO requestCommentDeleteDTO){

        return commentService.delete(requestCommentDeleteDTO);
    }

}
