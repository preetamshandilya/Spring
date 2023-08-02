package com.example.RestConvention.repository;

import com.example.RestConvention.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "select * from comments where id =:commentId AND article_id=:articleId",nativeQuery = true)
    Comment getCommentById(Integer articleId,Integer commentId);
}
