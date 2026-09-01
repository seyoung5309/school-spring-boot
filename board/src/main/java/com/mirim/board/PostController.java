package com.mirim.board;

import ch.qos.logback.core.joran.action.ResourceAction;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/posts")
public class PostController {

    // 1. 5번 게시물, 10번 게시물을 어떻게 읽을까?
    // 2. 검색어처럼 있어도 되고 없어도 되는 값
    // 3. 많은 데이터(게시글) 어떻게 보낼까?

//    @GetMapping("/search")
//    public String searchPost(@RequestParam String keyword) {
//        return keyword + "(으)로 검색된 결과입니다.";
//    }

    @GetMapping
    public String getPosts(@RequestParam(required = false) String keyword) {
        //return "게시글의 목록입니다.";
        if (keyword != null) {
            return keyword + "(으)로 검색된 결과입니다.";
        }
        return "게시글의 목록입니다.";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        // 게시글 번호가 10번보다 크면 게시글이 존재 하지 않는다.
        if (id > 10) {
            // 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("존재하지 않는 게시글입니다.");
//            ResponseEntity.notFound();
        } else if (id <= 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("잘못된 요청입니다.");
        }else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(id + "번 게시글입니다.");
        }
//        return id + "번 게시글입니다";
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

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);

        //return "[" + title + "] 게시글이 등록되었습니다. 내용 : " + content;
    }
}
