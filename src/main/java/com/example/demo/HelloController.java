package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Webの窓口になる
@CrossOrigin(origins = "http://localhost:5173") // React(5173)からのアクセスを許可する
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Java Web World!";
    }
}