package com.dailypulse.news_service.event;

import com.dailypulse.news_service.model.NewsArticle;
import org.springframework.context.ApplicationEvent;

public class ArticlePublishedEvent extends ApplicationEvent {

    private final NewsArticle newsArticle;

    public ArticlePublishedEvent(Object source, NewsArticle newsArticle) {
        super(source);
        this.newsArticle = newsArticle;
    }

    public NewsArticle getNewsArticle() {
        return this.newsArticle;
    }
}
