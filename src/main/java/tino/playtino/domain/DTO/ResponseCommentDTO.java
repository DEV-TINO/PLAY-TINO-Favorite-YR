package tino.playtino.domain.DTO;

import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponseCommentDTO {
    UUID commentId;
    UUID userId;
    String content;
    Integer heartCount;
    LocalDateTime uploadTime;
}
