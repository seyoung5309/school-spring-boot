package com.mirim.board;

import com.mirim.board.dto.ApiErrorResponse;
import com.mirim.board.dto.MemberResponse;
import com.mirim.board.dto.SignupRequest;
import com.mirim.board.exception.ApiException;
import com.mirim.board.service.MemberService;
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

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiErrorResponse> handleApiException(ApiException e) {
        ApiErrorResponse body = new ApiErrorResponse(e.getStatus().value(), e.getMessage(), Map.of());
        return ResponseEntity.status(e.getStatus()).body(body);
    }
}
