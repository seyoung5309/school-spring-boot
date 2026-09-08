package com.mirim.board;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {

        // 1. 필요한 객체들을 찾아서 만들어 둡니다.
        // 2. 창고에 저장 한다. (창고 = 스프링 컨테이너)
        // 3. 내장 톰캣(서버)을 띄운다. 그에 맞는 포트(8080)가 열린다.
        // 4. 요청을 받아서 요청이 오면 알맞는 코드로 넘겨준다.

        ApplicationContext context = SpringApplication.run(BoardApplication.class, args);
        Notifier notifier = context.getBean(Notifier.class);
        notifier.send("컨테이너에서 직접 꺼낸 테스트 메시지입니다.");

        try {
            SmsNotifier smsNotifier = context.getBean(SmsNotifier.class);
            smsNotifier.send("컨테이너에서 직접 꺼낸 테스트 메시지입니다.");
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("SmsNOtifier는 컨테이너에 없음: "+ e.getMessage());
        }

        System.out.println("등록된 Bean 개수: " + context.getBeanDefinitionNames().length);
        System.out.println("emailNotifier 등록 여부: " + context.containsBean("emailNotifier"));
        System.out.println("smsNotifier 등록 여부: " + context.containsBean("smsNotifier"));
    }

}