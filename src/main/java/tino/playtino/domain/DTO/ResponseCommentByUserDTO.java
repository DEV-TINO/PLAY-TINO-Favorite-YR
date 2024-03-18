package tino.playtino.domain.DTO;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import tino.playtino.Bean.Small.GetBooleanUserHeartExistsBean;
import tino.playtino.domain.Comment;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseCommentByUserDTO {
    UUID commentId;
    UUID userId;
    String content;
    Integer heartCount;
    LocalDateTime uploadTime;

    Boolean userHeartExists;
}
