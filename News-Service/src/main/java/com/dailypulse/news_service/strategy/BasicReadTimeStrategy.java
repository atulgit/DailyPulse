package com.dailypulse.news_service.strategy;

import org.springframework.stereotype.Component;

@Component("basicReadTimeStrategy")
public class BasicReadTimeStrategy implements ReadTimeStrategy{
    @Override
    public int calculateReadTime() {
        return 0;
    }
}
