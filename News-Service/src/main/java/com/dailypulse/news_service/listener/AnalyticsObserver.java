package com.dailypulse.news_service.listener;

import com.dailypulse.news_service.event.ArticlePublishedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsObserver {

    @EventListener
    public void OnArticlePublished(ArticlePublishedEvent articlePublishedEvent) {
        // In a real-world scenario, you would send data to a service like Google Analytics or a custom data warehouse.
        System.out.println("Analytics Service: Tracking article publication. Article ID: " + articlePublishedEvent.getNewsArticle().getId());
        // This observer is also completely decoupled from the publishing service.
    }
}
