package com.dailypulse.news_service.repository;

import com.dailypulse.news_service.model.NewsArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for NewsArticle entities.
 * Spring Data JPA provides a set of powerful features by just extending JpaRepository.
 * It automatically provides methods for common database operations (e.g., save(), findById(), findAll(), delete()).
 */
@Repository // Marks this interface as a Spring repository, enabling component scanning.
public interface NewsArticleRepository extends JpaRepository<NewsArticle, Long> {
    // We don't need to write any code here. Spring Data JPA will automatically
    // generate the necessary implementation at runtime based on the method names.
    // For example, if we needed to find an article by its title, we could simply declare:
    // List<NewsArticle> findByTitle(String title);

    @Query("SELECT n FROM NewsArticle n WHERE n.category = ?1")
    List<NewsArticle> findByCategory(String category);
}
