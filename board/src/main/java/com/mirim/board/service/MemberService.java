package com.mirim.board.service;

import com.mirim.board.Member;
import com.mirim.board.dto.MemberResponse;
import com.mirim.board.dto.SignupRequest;
import com.mirim.board.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 생성자 주입
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public MemberResponse signup(SignupRequest request) {
        String password = request.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        Member member = new Member(request.getLoginId(), encodedPassword, request.getName());
        memberRepository.save(member);
        return MemberResponse.from(member);
    }

    public MemberResponse getMember(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            return null;
        }
        return MemberResponse.from(member);
    }
}
