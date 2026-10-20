package com.mirim.board.service;

import com.mirim.board.Member;
import com.mirim.board.dto.MemberResponse;
import com.mirim.board.dto.SignupRequest;
import com.mirim.board.exception.ApiException;
import com.mirim.board.repository.MemberRepository;
import org.springframework.http.HttpStatus;
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
        if (memberRepository.existsByLoginId(request.getLoginId())) {
            throw new ApiException(HttpStatus.CONFLICT, "이미 사용 중인 아이디입니다.");
        }
        String password = request.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        Member member = new Member(request.getLoginId(), encodedPassword, request.getName());
        memberRepository.save(member);
        return MemberResponse.from(member);
    }

    public MemberResponse getMember(Long id) {
        Member member = memberRepository.findById(id).orElse(null);
        if (member == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다.");
        }
        return MemberResponse.from(member);
    }
}
