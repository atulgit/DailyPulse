package com.dailypulse.news_service.listener;

import com.dailypulse.news_service.config.RabbitMQConfig;
import com.dailypulse.news_service.event.ArticlePublishedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RabbitMQListener {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleArticlePublishedEvent(ArticlePublishedEvent event) {
        logger.info("Received article published event from RabbitMQ: {}", event.getNewsArticle());
        // In a real-world scenario, this is where you would call
        // another service to send a push notification, update a search index, etc.
        logger.info("Asynchronously processing article: {}...", event.getNewsArticle().getTitle());
        // Simulate a long-running process
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        logger.info("Finished asynchronous processing for article: {}.", event.getNewsArticle().getTitle());
    }
}
