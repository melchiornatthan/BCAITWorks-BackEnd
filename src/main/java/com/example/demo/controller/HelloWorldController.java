package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

    @GetMapping
    public String index() {
        return "Hello World";
    }

    @GetMapping("hi")
    public String hi() {
        return "Hi World";
    }
}