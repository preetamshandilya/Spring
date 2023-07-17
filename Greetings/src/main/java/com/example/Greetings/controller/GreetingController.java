package com.example.Greetings.controller;

import com.example.Greetings.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

    @Autowired
    public GreetingService service;

    public static final Logger logger= LoggerFactory.getLogger(GreetingController.class);
    @GetMapping("/welcome")
    public @ResponseBody String greeting(){
        logger.info("Greeting Controller executed!");
        return service.message();

    }
}


