package com.dailypulse.news_service.service;

import com.dailypulse.news_service.model.NewsArticle;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendNotification(NewsArticle newsArticle) {
        System.out.println("Attempting to send notification for article: " + newsArticle.getTitle());
        // Simulate a failure in the notification system for a specific category.
        if ("Sports".equalsIgnoreCase(newsArticle.getCategory())) {
            throw new RuntimeException("Notification service failed for Sports category. Rolling back article creation.");
        }
        System.out.println("Notification sent successfully!");
    }
}
