package tino.playtino.domain.DTO;

import lombok.Data;

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
