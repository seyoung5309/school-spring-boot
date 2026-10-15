package com.mirim.board;

import com.mirim.board.dto.MemberResponse;
import com.mirim.board.dto.SignupRequest;
import com.mirim.board.service.MemberService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member")
    public ResponseEntity<MemberResponse> signup(@RequestBody SignupRequest request) {
        MemberResponse created = memberService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<?> getMember(@PathVariable Long id) {
        MemberResponse member = memberService.getMember(id);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("존재하지 않는 회원입니다.");
        }
        return ResponseEntity.ok(member);
    }
}
