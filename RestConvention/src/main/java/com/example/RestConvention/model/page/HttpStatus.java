package com.example.RestConvention.model.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class HttpStatus <T>{
    private int statusCode;
    private String statusMessage;

}
