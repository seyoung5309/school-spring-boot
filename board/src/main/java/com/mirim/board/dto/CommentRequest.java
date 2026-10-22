package com.mirim.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentRequest {
    @NotBlank(message="댓글 내용은 필수입니다.")
    @Size(max = 500, message = "댓글은 500자 이하여야 합니다.")
    private String content;

    public String getContent() {
        return content;
    }
}
