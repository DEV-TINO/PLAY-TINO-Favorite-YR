package tino.playtino.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RequestCommentSaveDTO {
    //UUID commentId;

    UUID userId;
    String content;
}
