package com.example.Welcome.controller;

import com.example.Welcome.pojo.Welcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")

public class WelcomeController {
    @Autowired
    public Welcome welcome;
    @GetMapping("/welcome")
    public @ResponseBody String welcome(){
        welcome.welcome();
        return "Welcome to SpringBoot!";

    }
}
