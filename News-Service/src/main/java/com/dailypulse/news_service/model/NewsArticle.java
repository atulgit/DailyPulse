package com.dailypulse.news_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

/**
 * Represents a news article entity in the database.
 * This class is a simple Plain Old Java Object (POJO) that corresponds to a table in our H2 database.
 * We're using Lombok annotations to automatically generate getters, setters, constructors, etc.
 */
@Entity // Marks this class as a JPA entity, meaning it maps to a database table.
@Data // A convenient shortcut annotation that bundles @Getter, @Setter, @ToString, @EqualsAndHashCode.
@NoArgsConstructor // Generates a no-argument constructor, which is required by JPA.
@AllArgsConstructor // Generates a constructor with all fields as arguments.
//@Getter
//@Setter
@Builder
public class NewsArticle {

    @Id // Specifies the primary key of the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures the primary key to be auto-incremented by the database.
    private Long id;

    private String title;
    private String content;
    private String author;
    private String category;
    private String format;
    private int readTime;
    private String publicationDate; // For simplicity, we will use a String for now, but in a real-world app, we'd use a more robust Date/Time type.
}

