package tino.playtino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tino.playtino.domain.*;
import tino.playtino.domain.DTO.*;
import tino.playtino.service.CommentService;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /*
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

    //댓글 전체 조회 - 최신순
    @GetMapping("/comment/all/time/{userId}")
    public List<ResponseCommentByUserDTO> readAllByTime(@PathVariable UUID userId){

        return commentService.readAllByTime(userId);
    }

    //댓글 전체 조회 - 추천순
    @GetMapping("/comment/all/heart/{userId}")
    public List<ResponseCommentByUserDTO> readAllByHeart(@PathVariable UUID userId){

        return commentService.readAllByHeart(userId);
    }
    */

    // 댓글 조회 - 페이징
    @GetMapping("/comment/all/{userId}/{criteria}")
    public List<ResponseCommentByUserDTO> readCommentAll(@PathVariable UUID userId,
                                                         @PathVariable String criteria,
                                                         @RequestParam(required = false, defaultValue = "0", value = "page") int pageNo){
        return commentService.readCommentAll(userId, pageNo, criteria);
    }

    //댓글 저장
    @PostMapping("/comment")
    public ResponseSuccess createComment(@RequestBody RequestCommentSaveDTO requestCommentSaveDTO){

        return commentService.createComment(requestCommentSaveDTO);
    }

    //댓글 수정
    @PutMapping("/comment")
    public ResponseSuccess updateComment(@RequestBody RequestCommentUpdateDTO requestCommentUpdateDTO){

        return commentService.updateComment(requestCommentUpdateDTO);
    }

    //댓글 삭제
    @DeleteMapping("/comment")
    public ResponseSuccess deleteComment(@RequestBody RequestCommentDeleteDTO requestCommentDeleteDTO){

        return commentService.deleteComment(requestCommentDeleteDTO);
    }

}
