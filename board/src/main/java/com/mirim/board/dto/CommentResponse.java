package com.mirim.board.dto;

import com.mirim.board.Comment;

import java.time.LocalDateTime;

public class CommentResponse {
    private final Long id;
    private final String content;
    private final AuthorResponse author;
    private final LocalDateTime createdAt;

    public CommentResponse(Long id, String content, AuthorResponse author, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getId(),
                comment.getContent(),
                AuthorResponse.from(comment.getAuthor()),
                comment.getCreatedAt()
        );
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public AuthorResponse getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
