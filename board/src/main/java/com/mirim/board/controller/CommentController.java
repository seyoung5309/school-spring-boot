package com.mirim.board.controller;

import com.mirim.board.Comment;
import com.mirim.board.dto.CommentRequest;
import com.mirim.board.dto.CommentResponse;
import com.mirim.board.login.SessionConst;
import com.mirim.board.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long postId,
                                                         @Valid @RequestBody CommentRequest request,
                                                         @SessionAttribute(name= SessionConst.LOGIN_MEMBER_ID) Long loginMemberId) {
        CommentResponse created = commentService.createComment(postId, request, loginMemberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getComments(postId));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId,
                                              @PathVariable Long commentId,
                                              @SessionAttribute(name= SessionConst.LOGIN_MEMBER_ID) Long loginMemberId) {
        commentService.deleteComment(commentId, loginMemberId);
        return ResponseEntity.noContent().build();
    }
}
