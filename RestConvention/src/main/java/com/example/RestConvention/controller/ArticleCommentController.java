package com.example.RestConvention.controller;

import com.example.RestConvention.model.entity.Comment;
import com.example.RestConvention.service.article.IArticleService;
import com.example.RestConvention.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles/{articleId}/comments")

public class ArticleCommentController {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private ICommentService commentService;


    @PostMapping()
    public @ResponseBody Comment create(@PathVariable Integer articleId, Comment comment){
        return commentService.create(articleId,comment);
    }

    @GetMapping()
    public @ResponseBody List<Comment> read(@PathVariable Integer articleId){
        return commentService.read(articleId);
    }

    @GetMapping("/{commentId}")
    public @ResponseBody Comment readById(@PathVariable Integer articleId,@PathVariable Integer commentId){
        return commentService.readById(articleId,commentId);
    }

    @PutMapping("/{commentId}")
    public @ResponseBody String update(@PathVariable Integer articleId,@PathVariable Integer commentId,@RequestBody Comment comment){
        return commentService.update(articleId,commentId,comment);
    }

    @DeleteMapping("/{commentId}")
    public @ResponseBody String delete(@PathVariable Integer articleId,@PathVariable Integer commentId){
        return commentService.delete(articleId,commentId);
    }
}
