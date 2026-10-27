package com.mirim.board.repository;

import com.mirim.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 게시글 아이디로 댓글 목록 불러오기
    List<Comment> findByPostId(Long postId);

    @Query("select c from Comment c join fetch c.author where c.post.id")
    List<Comment> findByPostIdWithAuthor(@Param("postId") Long postId);
}
