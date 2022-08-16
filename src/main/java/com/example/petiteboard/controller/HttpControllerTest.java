package com.example.petiteboard.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
    private static final String TAG = "HTTP_TEST : ";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = Member.builder().username("Grusha").password("123").email("grusha@gmail.com").build();
        System.out.println(TAG + "getter : " + m.getId());
        m.setId(500);
        System.out.println(TAG + "setter : " + m.getId());
        System.out.println(TAG + "getter : " + m.getUsername());
        m.setUsername("Kim");
        System.out.println(TAG + "setter : " + m.getUsername());
        return "Lombok test finished";
    }
}
