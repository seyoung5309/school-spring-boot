package com.mirim.board.dto;

import com.mirim.board.Member;

import java.time.LocalDateTime;

public class MemberResponse {
    private final Long id;
    private final String loginId;
    private final String name;
    private final LocalDateTime createdAt;

    public MemberResponse(Long id, String loginId, String name, LocalDateTime createdAt) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getLoingId(),
                member.getName(),
                member.getCreatedAt()
        );
    }

    public Long getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
