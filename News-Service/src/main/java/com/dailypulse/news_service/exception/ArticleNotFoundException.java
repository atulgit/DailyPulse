package com.dailypulse.news_service.exception;

import com.dailypulse.news_service.service.ArticlePublisherService;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String message) {
        super(message);
    }
}
