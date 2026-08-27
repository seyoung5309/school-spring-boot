package com.mirim.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {
    // 브라우저 -> 내장 톰캣 -> 교통정리 담당 -> HelloController.hello()
    // 교통정리 담당 = DispatcherServlet
    // DispatcherServlet이 하는 일
    // - 주소를 보고 어느 메서드로 보낼지 고른다.
    // - 목적지가 없다면 404를 응답한다.

    // CRUD: Create(POST) / Read(GET) / Update(UPDATE) / Delete(DELETE)
    // 브라우저에서 주소창으로 직접 요청할 때는 GET 이외의 메서드는 보낼 수 없다.
    // 1. 게시글 작성하는 건 어떻게 테스트할까?
    // 2. RestController, GetMapping은 뭐하는 애들일까?

    @Value("${my.message}")
    private String message;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello")
    public String hello2() {
        return message;
//        throw new RuntimeException("오류 발생!");
    }

    @GetMapping("/hello-map")
    public Map<String, Object> helloMap() {
        return Map.of("name", "김미림", "grade", 2);
    }
}
