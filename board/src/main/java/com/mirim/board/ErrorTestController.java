package com.mirim.board;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorTestController {

    @GetMapping("/error-test")
    public String testError() {
        String value = null;

        return value.toUpperCase();
    }
}