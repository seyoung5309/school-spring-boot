package com.mirim.board.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {

    private final JdbcTemplate jdbcTemplate;
    private final List<Map<String, Object>> posts = new ArrayList<>();
    private Long nextId = 1L;

    private final RowMapper<Map<String, Object>> postRowMapper = (rs, rowNum) -> {
        Map<String, Object> post = new HashMap<>();
        post.put("id", rs.getLong("id"));
        post.put("title", rs.getString("title"));
        post.put("content", rs.getString("content"));
        return post;
    };

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> save(Map<String, Object> post) {
        post.put("id", nextId++);
        posts.add(post);
        return post;
    }

    public boolean existsById(Long id) {
        return findById(id) != null;
    }

    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM posts";
        jdbcTemplate.query(sql, postRowMapper);
        return posts;
    }

    public Map<String, Object> findById(Long id) {
        // 게시글 전체: posts
        for (Map<String, Object> post : posts) {
            if (post.get("id").equals(id)) {
                return post;
            }
        }
        return null;
    }

    public long count() {
        return posts.size();
    }

    public List<Map<String, Object>> findByKeyword(String keyword) {
        List<Map<String, Object>> result = new ArrayList<>();

        // 1. 전체 게시글을 순회하며 키워드를 포함하는지 확인
        for (Map<String, Object> post : posts) {
            String title = (String) post.get("title");
            if (title != null && title.contains(keyword)) {
                result.add(post);
            }
        }

        // 2. 포함한다면 result에 게시글 정보 추가


        return result;
    }

    public boolean deleteById(Long id) {
        Map<String, Object> post = findById(id);
        if (post == null) {
            return false;
        }
        posts.remove(post);
        return true;
    }
}
