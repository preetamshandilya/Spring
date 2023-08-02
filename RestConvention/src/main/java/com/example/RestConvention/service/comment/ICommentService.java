package com.example.RestConvention.service.comment;

import com.example.RestConvention.model.entity.Comment;

import java.util.List;

public interface ICommentService {

    public Comment create(Integer articleId,Comment comment);

    public List<Comment> read(Integer articleId);
    public Comment readById(Integer articleId,Integer commentId);

    public String update(Integer articleId,Integer commentId,Comment updatedArticle);
    public String delete(Integer articleId,Integer commentId);
}
