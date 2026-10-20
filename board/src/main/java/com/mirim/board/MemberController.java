package com.mirim.board;

import com.mirim.board.dto.ApiErrorResponse;
import com.mirim.board.dto.LoginRequest;
import com.mirim.board.dto.MemberResponse;
import com.mirim.board.dto.SignupRequest;
import com.mirim.board.exception.ApiException;
import com.mirim.board.login.SessionConst;
import com.mirim.board.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member")
    public ResponseEntity<MemberResponse> signup(@Valid @RequestBody SignupRequest request) {
        MemberResponse created = memberService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponse> getMember(@Valid @PathVariable Long id) {
        MemberResponse member = memberService.getMember(id);
        return ResponseEntity.ok(member);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponse> login(@Valid @RequestBody LoginRequest request, HttpServletRequest httpRequest) {
        MemberResponse member = memberService.login(request);

        // 사용자별 보관함.
        HttpSession session = httpRequest.getSession();

        // 로그인에 성공했으니 번호표 교체
        httpRequest.changeSessionId();

        // 사용자 PT 저장

        session.setAttribute(SessionConst.LOGIN_MEMBER_ID, member.getId());
        return ResponseEntity.ok(member);
    }
}
