package com.markson.controlefinanceiro;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ola")
public class HelloWorld {
    @GetMapping
    public ResponseEntity olaMundo() {
        return ResponseEntity.ok("Hello World");
    }
}
