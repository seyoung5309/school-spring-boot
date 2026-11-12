package com.mirim.board.controller;

import com.mirim.board.dto.PostRequest;
import com.mirim.board.dto.PostResponse;
import com.mirim.board.login.SessionConst;
import com.mirim.board.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<?> getPosts(@RequestParam(required = false) String keyword) {
        if (keyword != null) {
            List<PostResponse> posts = postService.searchPosts(keyword);
            return ResponseEntity.ok(posts);
        }
        List<PostResponse> posts =postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        PostResponse post = postService.getPost(id);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/count")
    public String getPostCount() {
        long postCount = postService.getPostCount();
        return "게시글 개수 : " + postCount + "개";
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostRequest request,
                                                   @SessionAttribute(name = SessionConst.LOGIN_MEMBER_ID) Long loginMemberId) {
        String title = request.getTitle();
        String content = request.getContent();

        PostResponse response = postService.createPost(title, content, loginMemberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(@Valid @PathVariable Long id, @RequestBody PostRequest request) {
        String title = request.getTitle();
        String content = request.getContent();

        PostResponse response = postService.updatePost(title, content, id);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,
                                           @SessionAttribute(name = SessionConst.LOGIN_MEMBER_ID) Long loginMemberId) {
        postService.deletePost(id, loginMemberId);
        return ResponseEntity.noContent().build();
    }
}
