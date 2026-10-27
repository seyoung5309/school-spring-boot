package com.mirim.board.repository;

import com.mirim.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // select * from posts where title = ''
//    List<Post> findByTitle(String keyword);

    //select * from posts where title like '%%'
    List<Post> findByTitleContaining(String keyword);

    @Query("select p from Post p join fetch p.author")
    List<Post> findAllWithAuthor();
}
