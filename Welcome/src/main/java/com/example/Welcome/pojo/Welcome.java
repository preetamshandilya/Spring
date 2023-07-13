package com.example.Welcome.pojo;

import org.springframework.stereotype.Service;

@Service
public class Welcome {
    public void welcome(){
        System.out.println("I'm up!");
        System.out.println("Status: 200 ok!");
    }
}
