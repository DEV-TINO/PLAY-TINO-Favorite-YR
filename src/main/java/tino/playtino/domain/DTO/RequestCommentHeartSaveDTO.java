package tino.playtino.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestCommentHeartSaveDTO {
    UUID commentId;
    UUID userId;
}