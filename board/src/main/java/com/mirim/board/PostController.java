package com.mirim.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {

    @GetMapping
    public String getPosts() {
        return "게시글의 목록입니다.";
    }

    @GetMapping("/count")
    public String getPostCount() {
        return "게시글 개수 : 0개";
    }

    @PostMapping
    public String createPost() {
        return "게시글이 등록되었습니다";
    }
}
