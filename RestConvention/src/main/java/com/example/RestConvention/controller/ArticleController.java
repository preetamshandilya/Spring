package com.example.RestConvention.controller;

import com.example.RestConvention.model.entity.Article;
import com.example.RestConvention.model.page.ArticlePage;
import com.example.RestConvention.service.article.IArticleService;
import com.example.RestConvention.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private IArticleService articleService;
    @PostMapping
    public @ResponseBody Article create(@RequestBody Article article){
        return articleService.create(article);
    }

//    @GetMapping
//    public @ResponseBody List<Article> read(){
//        return articleService.read();
//    }

    @GetMapping("/{articleId}")
    public @ResponseBody Article readById(@PathVariable Integer articleId){
        return articleService.readById(articleId);
    }

    @PutMapping("/{articleId}")
    public @ResponseBody String update(@PathVariable Integer articleId ,@RequestBody Article article){
        return articleService.update(articleId,article);
    }

    @DeleteMapping("/{articleId}")
    public  @ResponseBody String delete(@PathVariable Integer articleId){
        return articleService.delete(articleId);
    }

    @GetMapping
    public @ResponseBody ArticlePage<Article> page(@RequestParam(value = "offset",defaultValue = "0") int offset,
                                                   @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                                   @RequestParam(value = "sortBy",defaultValue = "id") String sortBy,
                                                   @RequestParam(value = "sortOrder",defaultValue = "asc") String sortOrder,
                                                   @RequestParam(value = "authorFilter",required = false) List<String> authorFilter,
                                                   @RequestParam(value = "categoryFilter",required = false) List<String> categoryFilter){
        return articleService.page(offset,pageSize,sortBy,sortOrder,authorFilter,categoryFilter);
    }

}
