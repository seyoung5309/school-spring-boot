package com.mirim.board.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepository {

    // 아직 진자 저장소는 없음.
    // 컨트롤러에서 쓰던 가짜 규칙 옮기기.
    public boolean existsById(Long id) {
        return id <= 10;
    }

    public List<Map<String, Object>> findAll() {
        return new ArrayList<>();
    }

    public long count() {
        return 0;
    }

    public List<Map<String, Object>> findByKeyword(String keyword) {
        return new ArrayList<>();
    }
}
