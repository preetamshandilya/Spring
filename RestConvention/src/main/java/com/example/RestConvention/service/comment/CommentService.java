package com.example.RestConvention.service.comment;

import com.example.RestConvention.model.entity.Article;
import com.example.RestConvention.model.entity.Comment;
import com.example.RestConvention.repository.CommentRepository;
import com.example.RestConvention.service.article.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private CommentRepository commentRepository;
    @Override
    @Transactional
    public Comment create(Integer articleId, Comment comment) {
        Article article= articleService.readById(articleId);
        comment.setArticle(article);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> read(Integer articleId) {
        Article article= articleService.readById(articleId);
        return article.getCommentList();
    }

    @Override
    public Comment readById(Integer articleId, Integer commentId) {
        return commentRepository.getCommentById(articleId,commentId);
    }

    @Override
    public String update(Integer articleId, Integer commentId, Comment updatedComment) {
        Comment existingComment=commentRepository.getCommentById(articleId,commentId);
        existingComment.setText(updatedComment.getText());
        commentRepository.save(existingComment);
        return "Updated Successfully!";
    }

    @Override
    public String delete(Integer articleId, Integer commentId) {
        Comment comment=commentRepository.getCommentById(articleId,commentId);
        commentRepository.delete(comment);
        return "Deleted Successfully!";
    }
}
