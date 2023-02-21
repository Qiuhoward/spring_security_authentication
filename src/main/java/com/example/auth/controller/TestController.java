package com.example.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <testController>
 */
@RestController
public class TestController {

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }
}
