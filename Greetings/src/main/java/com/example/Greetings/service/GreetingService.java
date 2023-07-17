package com.example.Greetings.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service

public class GreetingService {
    public static final Logger logger= LoggerFactory.getLogger(GreetingService.class);

    public String message(){
        logger.info("I'm up!");
        logger.info("Status: 200 ok");
        logger.trace("Greeting Service executed!");
        return "Welcome to SpringBoot";
    }
}
