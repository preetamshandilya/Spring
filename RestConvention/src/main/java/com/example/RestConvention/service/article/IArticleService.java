package com.example.RestConvention.service.article;

import com.example.RestConvention.model.entity.Article;
import com.example.RestConvention.model.page.ArticlePage;

import java.util.List;

public interface IArticleService {

    public Article create(Article article);

    public List<Article> read();
    public Article readById(Integer id);

    public String update(Integer articleId,Article updatedArticle);
    public String delete(Integer id);

    public ArticlePage<Article> page(int offset,int pageSize,String sortBy,String sortOrder,List<String> authorFilter,List<String> categoryFilter);
}
