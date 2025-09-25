package com.dailypulse.news_service;

import com.dailypulse.news_service.model.NewsArticle;
import com.dailypulse.news_service.repository.NewsArticleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;

import java.util.Date;


@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
public class DailyPulseApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyPulseApplication.class, args);
    }

    /**
     * A CommandLineRunner bean that will execute code right after the application starts.
     * We'll use this to pre-populate our H2 database with some sample data.
     * This is a great way to ensure our application has data to work with immediately.
     */
    @Bean
    public CommandLineRunner dataLoader(NewsArticleRepository newsArticleRepository) {

        NewsArticle article = NewsArticle.builder().category("").id(1L).build();

        return args -> {

//            newsArticleRepository.save(new NewsArticle(null, "First News Title", "This is the content of the first news article. It's all about the latest trends.", "John Doe", "Technology", new Date().toString()));
//            newsArticleRepository.save(new NewsArticle(null, "Second Article on Finance", "Here's an analysis of the stock market performance.", "Jane Smith", "Finance", new Date().toString()));
//            newsArticleRepository.save(new NewsArticle(null, "Local Sports News", "Our local team won the championship last night in a stunning game.", "Mike Brown", "Sports", new Date().toString()));
        };
    }
}
