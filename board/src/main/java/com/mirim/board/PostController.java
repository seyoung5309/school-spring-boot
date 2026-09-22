package com.mirim.board;

import com.mirim.board.repository.PostRepository;
import com.mirim.board.service.PostService;
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
            List<Post> posts = postService.searchPosts(keyword);
            return ResponseEntity.ok(posts);
        }
        List<Post> posts =postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {

        Post post = postService.getPost(id);

        if (id < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 번호는 1 이상이어야 합니다.");
        }

        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시물입니다.");
        }

        return ResponseEntity.ok(post);
    }

    @GetMapping("/count")
    public String getPostCount() {
        long postCount = postService.getPostCount();
        return "게시글 개수 : " + postCount + "개";
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post response = postService.createPost(post.getTitle(), post.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Post response = postService.updatePost(post.getTitle(), post.getContent(), id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id) {

        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 번호는 1 이상이어야 합니다");
        }
        if (id > 10) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시물입니다.");
        }

        boolean deleted = postService.deletePost(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시물입니다.");
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
