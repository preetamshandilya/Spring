package com.example.RestConvention.model.page;

import com.example.RestConvention.model.entity.Article;
import lombok.Getter;

import java.util.List;

@Getter
public class Data <T>{
    private int pageNumber;
    private int pageSize;
    private int maxAvailablePage;
    private List<Article> articles;

    public Data(int page_no, int page_size, int max_available_page, List<Article> articles) {
        this.pageNumber = page_no;
        this.pageSize = page_size;
        this.maxAvailablePage = max_available_page;
        this.articles = articles;
    }

    public Data() {
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setMaxAvailablePage(int maxAvailablePage) {
        this.maxAvailablePage = maxAvailablePage;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
