package com.mirim.board.repository;

import com.mirim.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 게시글 아이디로 댓글 목록 불러오기
    List<Comment> findByPostId(Long postId);
}
