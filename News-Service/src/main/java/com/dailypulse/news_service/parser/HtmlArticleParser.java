package com.dailypulse.news_service.parser;

import org.springframework.stereotype.Component;

/**
 * Concrete implementation of NewsArticleParser for HTML content.
 * This class simply returns the content as is, since it's already in the desired format.
 */
@Component("htmlParser")
public class HtmlArticleParser implements NewsArticleParser {

    @Override
    public String parse(String content) {
        // In a real-world scenario, you might sanitize or validate the HTML here.
        System.out.println("Processing HTML content...");
        return content;
    }
}
