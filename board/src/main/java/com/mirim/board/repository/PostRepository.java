package com.mirim.board.repository;

import com.mirim.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContaining(String keyword);

}
