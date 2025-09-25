package com.dailypulse.news_service.controller;

import com.dailypulse.news_service.model.NewsArticle;
import com.dailypulse.news_service.service.NewsArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class NewsArticleController {
    private NewsArticleService newsArticleService;

    @Autowired
    public NewsArticleController(NewsArticleService newsArticleService) {
        this.newsArticleService = newsArticleService;
    }

    /**
     * GET endpoint to retrieve all news articles.
     * Accessible at: GET http://localhost:8080/api/articles
     * @return A list of all articles with an HTTP 200 OK status.
     */
    @GetMapping
    public ResponseEntity<List<NewsArticle>> getAllArticles() {
        return new ResponseEntity<>(newsArticleService.getAllArticles(), HttpStatus.OK);
    }

    /**
     * GET endpoint to retrieve a single article by its ID.
     * Accessible at: GET http://localhost:8080/api/articles/{id}
     * @param id The ID of the article.
     * @return The article with an HTTP 200 OK status if found, or HTTP 404 Not Found otherwise.
     */
    @GetMapping("/{id}")
    public ResponseEntity<NewsArticle> getArticleById(Long id) {
        Optional<NewsArticle> article = newsArticleService.getArticleById(id);
        return article.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET endpoint to retrieve articles by a specific category.
     * Accessible at: GET http://localhost:8080/api/articles/category/{category}
     * @param category The category to search for.
     * @return A list of articles matching the given category.
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<NewsArticle>> getArticlesByCategory(@PathVariable String category) {
        List<NewsArticle> articles = newsArticleService.getArticlesByCategoryId(category);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    /**
     * POST endpoint to create a new news article.
     * Accessible at: POST http://localhost:8080/api/articles
     * @param newsArticle The article data from the request body.
     * @return The created article with an HTTP 201 Created status.
     */
    @PostMapping
    public ResponseEntity<NewsArticle> createArticle(@RequestBody NewsArticle newsArticle) {
        NewsArticle createdArticle = newsArticleService.createArticle(newsArticle);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    /**
     * PUT endpoint to update an existing news article.
     * Accessible at: PUT http://localhost:8080/api/articles/{id}
     * @param id The ID of the article to update.
     * @param updatedArticle The updated article data.
     * @return The updated article with an HTTP 200 OK status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<NewsArticle> updateArticle(@PathVariable Long id, @RequestBody NewsArticle updatedArticle) {
        NewsArticle article = newsArticleService.updateArticle(updatedArticle);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    /**
     * DELETE endpoint to remove an article by its ID.
     * Accessible at: DELETE http://localhost:8080/api/articles/{id}
     * @param id The ID of the article to delete.
     * @return An HTTP 204 No Content status upon successful deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        newsArticleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
