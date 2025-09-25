package com.dailypulse.news_service.parser;

import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Parser;

/**
 * Concrete implementation of NewsArticleParser for Markdown content.
 * This uses a third-party library to convert Markdown text to HTML.
 */
@Component("markdownParser")
public class MarkdownArticleParser implements NewsArticleParser{
//    private final Parser parser = Parser.builder().build();
//    private final HtmlRenderer renderer = HtmlRenderer.builder().build();

    @Override
    public String parse(String content) {
        System.out.println("Converting Markdown to HTML...");
        // Use the commonmark library to convert markdown to HTML.
//        return renderer.render(parser.parse(rawContent));
        return content;
    }
}
