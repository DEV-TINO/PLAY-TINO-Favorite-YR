package tino.playtino.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestCommentDeleteDTO {
    UUID commentId;
}
