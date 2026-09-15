package com.mirim.board.repository;

import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
    // 아직 진자 저장소는 없음.
    // 컨트롤러에서 쓰던 가짜 규칙 옮기기.
    public boolean existsById(Long id) {
        return id <= 10;
    }
}
