package com.mirim.board.service;

import com.mirim.board.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Map<String, Object> getPost(Long id) {
        if (postRepository.existsById(id) == false) {
            return null;
        }

        Map<String, Object> post = new HashMap<>();
        post.put("id", id);
        post.put("title", "게시글 제목");
        post.put("content", "게시글 내용");
        return post;
    }
}
