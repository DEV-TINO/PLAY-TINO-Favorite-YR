package tino.playtino.Bean;

import org.springframework.stereotype.Component;
import tino.playtino.domain.Comment;
import tino.playtino.domain.ResponseSuccess;

import java.util.UUID;

@Component
public class CheckUserCommentBean {

    //댓글이 사용자가 작성한 댓글인지 확인하는 기능
    public Boolean exec(Comment comment, UUID userId){

        //userId를 댓글의 userId와 비교 -> 같으면 true, 아니면 false 반환
        return comment.getUserId().equals(userId);
    }
}
