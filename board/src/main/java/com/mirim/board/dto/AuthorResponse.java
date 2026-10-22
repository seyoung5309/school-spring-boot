package com.mirim.board.dto;

import com.mirim.board.Member;

public class AuthorResponse {
    private final Long id;
    private final String name;

    public AuthorResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public static AuthorResponse from (Member member) {
        return new AuthorResponse(member.getId(), member.getName());
    }
}
