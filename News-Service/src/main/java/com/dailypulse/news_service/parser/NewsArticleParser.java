package com.dailypulse.news_service.parser;

/**
 * Interface for parsing news article content.
 * This is the common interface that our concrete parsers will implement.
 */
public interface NewsArticleParser {
    /**
     * Parses the raw content and returns the processed output.
     * @param rawContent The raw content of the news article.
     * @return The processed content.
     */
    String parse(String content);
}
