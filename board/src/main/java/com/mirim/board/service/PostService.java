package com.mirim.board.service;

import com.mirim.board.Notifier;
import com.mirim.board.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final Notifier notifier;

    public PostService(PostRepository postRepository, Notifier notifier) {
        this.postRepository = postRepository;
        this.notifier = notifier;
    }

    public Map<String, Object> getPost(Long id) {
        return postRepository.findById(id);
    }

    public Map<String, Object> createPost(String title, String content) {
        Map<String, Object> post = new HashMap<>();
        post.put("title", title);
        post.put("content", content);

        Map<String, Object> savedPost = postRepository.save(post);

        // post.put("message", "게시글이 등록되었습니다.");

        notifier.send(title + "게시글이 등록되었습니다.");

        return savedPost;
    }

    public Map<String, Object> updatePost(String title, String content, Long id) {
        Map<String, Object> post = postRepository.findById(id);
        if (post == null) {
            return null;
        }

        post.put("title", title);
        post.put("content", content);
        return post;
    }

    public boolean deletePost(Long id) {
        return postRepository.deleteById(id);
    }

    public List<Map<String, Object>> getAllPosts() {
        return postRepository.findAll();
    }

    public long getPostCount() {
        return postRepository.count();
    }

    public List<Map<String, Object>> searchPosts(String keyword) {
        return postRepository.findByKeyword(keyword);
    }
}
