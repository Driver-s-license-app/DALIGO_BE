package com.daligo.domain.hello.controller;

import com.daligo.domain.hello.service.HelloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class HelloController {
    private final HelloService helloService;

}
