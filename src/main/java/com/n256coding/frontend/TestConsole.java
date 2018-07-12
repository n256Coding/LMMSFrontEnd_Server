package com.n256coding.frontend;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestConsole {
    public static void main(String[] args) {
        String pwd = new BCryptPasswordEncoder(11).encode("pwd");
        System.out.println(pwd);
    }
}
