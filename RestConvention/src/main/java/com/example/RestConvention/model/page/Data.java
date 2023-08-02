package com.example.RestConvention.model.page;

import com.example.RestConvention.model.entity.Article;

import java.util.List;

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

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getMaxAvailablePage() {
        return maxAvailablePage;
    }

    public void setMaxAvailablePage(int maxAvailablePage) {
        this.maxAvailablePage = maxAvailablePage;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
