package com.dailypulse.news_service.service;

import com.dailypulse.news_service.exception.ArticleNotFoundException;
import com.dailypulse.news_service.model.NewsArticle;
import com.dailypulse.news_service.repository.NewsArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing business logic related to NewsArticle entities.
 * The @Service annotation marks this class as a Spring service component.
 * It acts as an intermediate layer between the Controller and the Repository.
 */
@Service
public class NewsArticleService {
    private final NewsArticleRepository newsArticleRepository;

    @Autowired
    public NewsArticleService(NewsArticleRepository newsArticleRepository) {
        this.newsArticleRepository = newsArticleRepository;
    }

    /**
     * Retrieves all news articles from the database.
     *
     * @return A list of all news articles.
     */
    @Cacheable(value = "allArticles", unless = "#result.isEmpty()")
    public List<NewsArticle> getAllArticles() {
        return newsArticleRepository.findAll();
    }

    /**
     * Retrieves a single news article by its ID.
     *
     * @param id The ID of the article to retrieve.
     * @return An Optional containing the article if found, or empty otherwise.
     */
    @Cacheable(value = "articleById", key = "#id")
    public Optional<NewsArticle> getArticleById(Long id) {
        return newsArticleRepository.findById(id);
    }

    /**
     * Retrieves news articles by a given category.
     *
     * @param category The category to search for.
     * @return A list of news articles matching the category.
     */
    @Cacheable(value = "articlesByCategory", key = "#category", unless = "#result.isEmpty()")
    public List<NewsArticle> getArticlesByCategoryId(String category) {
        return newsArticleRepository.findByCategory(category);
    }

    /**
     * Saves a new news article to the database.
     * The @Transactional annotation ensures that the entire method runs as a single database transaction.
     * If any part of the method fails, all changes will be rolled back, guaranteeing data consistency.
     *
     * @param article The article to save.
     * @return The saved article, including its generated ID.
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    // This is a crucial annotation for data consistency.
    @CacheEvict(value = {"allArticles", "articleByCategory"}, allEntries = true)
    public NewsArticle createArticle(NewsArticle newsArticle) {
        return newsArticleRepository.save(newsArticle);
    }

    /**
     * Deletes a news article by its ID.
     *
     * @param id The ID of the article to delete.
     */
    @Transactional
    @CacheEvict(value = "articleById", key = "#id")
//    @CacheEvict(value = {"allArticles"}, allEntries = true)
    @Cacheable(value = "articlesByCategory", key = "#root.target.getArticleById(#id).category")
    public void deleteArticle(Long id) {
        newsArticleRepository.deleteById(id);
    }

    @CacheEvict(value = {"allArticles", "articlesByCategory"}, allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED)
    public NewsArticle updateArticle(NewsArticle newsArticle) {
        NewsArticle existingArticle = newsArticleRepository.findById(newsArticle.getId())
                .orElseThrow(() -> new ArticleNotFoundException("Article not found with Id  " + newsArticle.getId()));
        existingArticle.setTitle(newsArticle.getTitle());
        existingArticle.setCategory(newsArticle.getCategory());

        return newsArticleRepository.save(existingArticle);
    }
}
