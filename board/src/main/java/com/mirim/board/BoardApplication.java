package com.mirim.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {

        // 1. 필요한 객체들을 찾아서 만들어 둡니다.
        // 2. 창고에 저장 한다. (창고 = 스프링 컨테이너)
        // 3. 내장 톰캣(서버)을 띄운다. 그에 맞는 포트(8080)가 열린다.
        // 4. 요청을 받아서 요청이 오면 알맞는 코드로 넘겨준다.

        SpringApplication.run(BoardApplication.class, args);
    }

}