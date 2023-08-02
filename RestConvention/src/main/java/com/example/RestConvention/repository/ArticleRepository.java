package com.example.RestConvention.repository;

import com.example.RestConvention.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> , JpaSpecificationExecutor<Article> {
    @Query(value = "select * from articles where id =:articleId",nativeQuery = true)
    Article getArticleById(Integer articleId);

}
