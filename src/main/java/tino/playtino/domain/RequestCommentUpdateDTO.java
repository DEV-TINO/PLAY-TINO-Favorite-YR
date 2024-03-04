package tino.playtino.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestCommentUpdateDTO {
    UUID commentId;
    UUID userId;
    String content;
}
