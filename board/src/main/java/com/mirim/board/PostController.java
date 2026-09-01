package com.mirim.board;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public String getPost(@PathVariable Long id) {
        return id + "번 게시글입니다";
    }

    @GetMapping("/count")
    public String getPostCount() {
        return "게시글 개수 : 0개";
    }

    @PostMapping
    public String createPost(@RequestBody Map<String, Object> request) {
        String title = (String) request.get("title");
        String content = (String) request.get("content");
        //Long user_id = (Long) request.get("user_id");

        // DB 로직

        return "[" + title + "] 게시글이 등록되었습니다. 내용 : " + content;
    }
}
