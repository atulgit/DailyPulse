package com.dailypulse.news_service.service;

import com.dailypulse.news_service.config.RabbitMQConfig;
import com.dailypulse.news_service.event.ArticlePublishedEvent;
import com.dailypulse.news_service.model.NewsArticle;
import com.dailypulse.news_service.parser.NewsArticleParser;
import com.dailypulse.news_service.parser.NewsArticleParserFactory;
import com.dailypulse.news_service.strategy.ReadTimeCalculator;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to manage the end-to-end process of publishing a news article.
 * This class orchestrates calls to other services and is the entry point
 * for a transactional operation.
 */
@Service
public class ArticlePublisherService {
    private final NewsArticleService newsArticleService;
    private final NotificationService notificationService;
    private final NewsArticleParserFactory parserFactory;
    private final ReadTimeCalculator readTimeCalculator;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ArticlePublisherService(NewsArticleService newsArticleService,
                                   NotificationService notificationService,
                                   NewsArticleParserFactory parserFactory,
                                   ReadTimeCalculator readTimeCalculator,
                                   RabbitTemplate rabbitTemplate) {
        this.newsArticleService = newsArticleService;
        this.notificationService = notificationService;
        this.parserFactory = parserFactory;
        this.readTimeCalculator = readTimeCalculator;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public NewsArticle publishArticle(NewsArticle newsArticle){
        // Step 1: Create the article.
        // The NewsArticleService.createArticle method (with Propagation.REQUIRED)
        // will join this existing transaction.
        System.out.println("Starting transaction to publish article.");

        NewsArticleParser parser = parserFactory.getArticleParser(newsArticle.getFormat());

        String parsedContent = parser.parse(newsArticle.getContent());
        newsArticle.setContent(parsedContent);

        newsArticle.setReadTime(readTimeCalculator.calculateReadTime());

        this.newsArticleService.createArticle(newsArticle);

        // Step 2: Send a notification.
        // If this call fails (throws a RuntimeException), the transaction manager will
        // automatically roll back the changes from Step 1.
        this.notificationService.sendNotification(newsArticle);

        ArticlePublishedEvent articlePublishedEvent = new ArticlePublishedEvent(this, newsArticle);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, articlePublishedEvent);
        System.out.println("Transaction completed successfully. Article published and notification sent.");

        return newsArticle;
    }
}
