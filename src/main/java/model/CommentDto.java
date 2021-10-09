package model;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long postId;
    private String body;
}
