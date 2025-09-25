package com.dailypulse.news_service.listener;

import com.dailypulse.news_service.event.ArticlePublishedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationObserver {

    @EventListener
    public void OnArticlePublished(ArticlePublishedEvent articlePublishedEvent) {
        // In a real-world scenario, you would integrate with an email sending service here.
        System.out.println("Email Notification Service: A new article has been published: " + articlePublishedEvent.getNewsArticle().getTitle());
        System.out.println("Sending email to all subscribers...");
        // This logic is completely decoupled from the ArticlePublisherService.
    }
}
