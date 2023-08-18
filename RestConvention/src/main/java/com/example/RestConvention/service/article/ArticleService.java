package com.example.RestConvention.service.article;

import com.example.RestConvention.model.entity.Article;
import com.example.RestConvention.model.page.ArticlePage;
import com.example.RestConvention.model.page.Data;
import com.example.RestConvention.model.page.HttpStatus;
import com.example.RestConvention.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService implements IArticleService  {
    @Autowired
    private ArticleRepository articleRepository;
    @Override
    public Article create(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public List<Article> read() {

        return articleRepository.findAll();
    }

    @Override
    public Article readById(Integer id) {

        return articleRepository.findById(id) .orElseThrow(() -> new EntityNotFoundException("Article not found with id: " + id));
    }

    @Override
    public String update(Integer articleId,Article updatedArticle) {
            Article existingArticle=articleRepository.getArticleById(articleId);
            existingArticle.setContent(updatedArticle.getContent());
            existingArticle.setTitle(updatedArticle.getTitle());
            existingArticle.setCategory(updatedArticle.getCategory());
            existingArticle.setCommentList(null);
            articleRepository.save(existingArticle);

            return "Updated Successfully!";



    }

    @Override
    public String delete(Integer id) {
        Optional<Article>article = articleRepository.findById(id);

        if(article.isEmpty()){
            return "Article Not Found!";

        }
        articleRepository.deleteById(id);
        return "Article Deleted!";
    }

    @Override
    public ArticlePage<Article> page(int offset, int pageSize, String sortBy, String sortOrder, List<String> authorFilter, List<String> categoryFilter) {
        try {
            Sort.Direction direction = Sort.Direction.ASC;
            if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
                direction = Sort.Direction.DESC;
            }

            Specification<Article> specification = (root, query, builder) -> {
                Predicate predicate = builder.conjunction();

                if (authorFilter != null && !authorFilter.isEmpty()) {
                    predicate = builder.and(predicate, root.get("author").in(authorFilter));
                }

                if (categoryFilter != null && !categoryFilter.isEmpty()) {
                    predicate = builder.and(predicate, root.get("category").in(categoryFilter));
                }

                return predicate;

            };
            Page<Article> page = articleRepository.findAll(specification, PageRequest.of(offset, pageSize, direction, sortBy));


            ArticlePage<Article> articlePage = new ArticlePage<>();
            HttpStatus<Article> httpStatus=new HttpStatus<>();
            httpStatus.setStatusCode(200);
            httpStatus.setStatusMessage("Success");
            articlePage.setStatus(httpStatus);

            //articlePage.setPage(page);

            Data<Article> articleData = new Data<>();
            articleData.setArticles(page.getContent());
            articleData.setPageNumber(page.getNumber()+1);
            articleData.setPageSize(page.getSize());
            articleData.setMaxAvailablePage(page.getTotalPages());
            articlePage.setData(articleData);



            return articlePage;
        } catch (Exception e) {
            ArticlePage<Article> errorPage = new ArticlePage<>();
            HttpStatus<Article> errorStatus = new HttpStatus<>();
            errorStatus.setStatusCode(500); // Using the status code directly
            errorStatus.setStatusMessage("Error: " + e.getMessage()); // Set the error message
            errorPage.setStatus(errorStatus);
            return errorPage;
        }
    }
}
