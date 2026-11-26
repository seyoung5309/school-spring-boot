package com.mirim.board.repository;

import com.mirim.board.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // select * from posts where title = ''
//    List<Post> findByTitle(String keyword);

    //select * from posts where title like '%%'
    // List<Post> findByTitleContaining(String keyword);

    @Query(value = "select p from Post p join fetch p.author where p.title like concat('%', :keyword, '%')",
            countQuery = "select count(p) from Post p where p.title like concat('%', :keyword, '%')")
    Page<Post> searchWithAuthor(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "select p from Post p join fetch p.author",
            countQuery = "select count(p) from Post p")
    Page<Post> findAllWithAuthor(Pageable pageable);
}
