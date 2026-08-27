package com.mirim.board;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${my.message}")
    private String message;

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello")
    public String hello2() {
//        return message;
        throw new RuntimeException("오류 발생!");
    }
}
