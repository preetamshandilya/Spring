package com.example.RestConvention.model.page;

import org.springframework.data.domain.Page;

public class ArticlePage <T>{
    private Page<T> page;
    private Data<T> data;

    public ArticlePage(Page<T> page, Data<T> data) {
        this.page = page;
        this.data = data;
    }

    public ArticlePage() {
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public Data<T> getData() {
        return data;
    }

    public void setData(Data<T> data) {
        this.data = data;
    }
}
