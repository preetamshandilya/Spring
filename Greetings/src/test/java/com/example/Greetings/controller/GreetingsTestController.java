package com.example.Greetings.controller;

import com.example.Greetings.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(GreetingController.class)
public class GreetingsTestController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingsTestController.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @Test
    public void testGreeting() throws Exception {
        logger.info("Running GreetingControllerTest...");

        when(greetingService.message()).thenReturn("Welcome to SpringBoot");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/welcome"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Welcome to SpringBoot"));
    }
}


