package com.mirim.board;

import com.mirim.board.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final Notifier notifier;

    public PostController(PostService postService, Notifier notifier) {
        this.postService = postService;
        this.notifier = notifier;
    }

    // 1. 5번 게시물, 10번 게시물을 어떻게 읽을까?
    // 2. 검색어처럼 있어도 되고 없어도 되는 값
    // 3. 많은 데이터(게시글) 어떻게 보낼까?

//    @GetMapping("/search")
//    public String searchPost(@RequestParam String keyword) {
//        return keyword + "(으)로 검색된 결과입니다.";
//    }

    @GetMapping
    public String getPosts(@RequestParam(required = false) String keyword) {
        System.out.println("이 요청을 처리하는 PostController: " + System.identityHashCode(this));
        //return "게시글의 목록입니다.";
        if (keyword != null) {
            return keyword + "(으)로 검색된 결과입니다.";
        }
        return "게시글의 목록입니다.";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        // 게시글 번호가 10번보다 크면 게시글이 존재 하지 않는다
//        if (id > 10) {
//            // 404
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("존재하지 않는 게시글입니다.");
////            ResponseEntity.notFound();
//        } else if (id <= 0) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("잘못된 요청입니다.");
//        } else {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(id + "번 게시글입니다.");
//        }
//        return id + "번 게시글입니다";

        Map<String, Object> post = postService.getPost(id);

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
        return "게시글 개수 : 0개";
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Map<String, Object> request) {
        String title = (String) request.get("title");
        String content = (String) request.get("content");
        //Long user_id = (Long) request.get("user_id");

        // DB 로직

        Map<String, Object> response = new HashMap<>();
        response.put("title", title);
        response.put("content", content);
        response.put("message", "게시글이 등록되었습니다.");

        // 발송
        notifier.send(title + "게시글이 등록되었습니다.");

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

        //return "[" + title + "] 게시글이 등록되었습니다. 내용 : " + content;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@RequestBody Map<String, Object> request, @PathVariable int id) {
        String title = (String) request.get("title");
        String content = (String) request.get("content");
        //Long user_id = (Long) request.get("user_id");

        // DB 로직

        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("title", title);
        response.put("content", content);
        response.put("message", "게시글이 수정되었습니다.");


        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

        //return "[" + title + "] 게시글이 등록되었습니다. 내용 : " + content;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("게시글 번호는 1 이상이어야 합니다");
        }
        if (id > 10) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 게시물입니다.");
        }

        // db에서 삭제한다고 치기

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
