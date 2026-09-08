package com.mirim.board;

import org.springframework.stereotype.Component;

@Component
public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("[이메일 발송] " + message);
    }
}
