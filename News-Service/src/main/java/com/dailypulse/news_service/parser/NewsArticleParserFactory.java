package com.dailypulse.news_service.parser;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Factory class to get the appropriate NewsArticleParser based on the format.
 * We use the ApplicationContext to get the correct bean by name. This is a common
 * and powerful way to implement a factory in Spring.
 */
@Component
public class NewsArticleParserFactory {
    private final ApplicationContext context;

    public NewsArticleParserFactory(ApplicationContext context) {
        this.context = context;
    }

    public NewsArticleParser getArticleParser(String format) {
        return switch (format.toUpperCase()) {
            case "HTML" -> context.getBean("htmlParser", HtmlArticleParser.class);
            case "MARKDOWN" -> context.getBean("markdownParser", MarkdownArticleParser.class);
            default -> throw new IllegalArgumentException();
        };
    }
}
