package com.example.RestConvention.model.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ArticlePage <T>{
   // private Page<T> page;
    private Data<T> data;
    private HttpStatus<T> status;
}
