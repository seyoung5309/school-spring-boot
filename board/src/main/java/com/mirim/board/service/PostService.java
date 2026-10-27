package com.mirim.board.service;

import com.mirim.board.Member;
import com.mirim.board.Notifier;
import com.mirim.board.Post;
import com.mirim.board.dto.PostResponse;
import com.mirim.board.exception.ApiException;
import com.mirim.board.repository.MemberRepository;
import com.mirim.board.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final Notifier notifier;

    public PostService(PostRepository postRepository, MemberRepository memberRepository, Notifier notifier) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.notifier = notifier;
    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다.");
        }
        return PostResponse.from(post);
    }

    public PostResponse createPost(String title, String content, Long loginMemberId) {
        Member author = memberRepository.findById(loginMemberId).orElse(null);
        if (author == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다.");
        }
        Post post = new Post(title, content, author);
        postRepository.save(post);

        return PostResponse.from(post);
    }

    public PostResponse updatePost(String title, String content, Long id) {
        Post post = postRepository.findById(id).orElse(null);

        if (post == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다.");
        }

        post.setTitle(title);
        post.setContent(content);
        postRepository.save(post);
        return PostResponse.from(post);
    }

    public void deletePost(Long id, Long loginMemberId) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다.");
        }
        if (!post.getAuthor().getId().equals(loginMemberId)) {
            throw new ApiException(HttpStatus.FORBIDDEN, "본인이 작성한 게시글만 삭제할 수 있습니다.");
        }
        postRepository.delete(post);
    }

    public List<PostResponse> getAllPosts() {
        return toResponses(postRepository.findAllWithAuthor());
    }

    public long getPostCount() {
        return postRepository.count();
    }

    public List<PostResponse> searchPosts(String keyword) {
        return toResponses(postRepository.findByTitleContaining(keyword));
    }

    private List<PostResponse> toResponses(List<Post> posts) {
        List<PostResponse> responses = new ArrayList<>();
        for (Post post: posts) {
            responses.add(PostResponse.from(post));
        }
        return responses;
    }
}
