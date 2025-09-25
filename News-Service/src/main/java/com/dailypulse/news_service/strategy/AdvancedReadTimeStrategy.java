package com.dailypulse.news_service.strategy;

import org.springframework.stereotype.Component;

@Component("advancedReadTimeStrategy")
public class AdvancedReadTimeStrategy implements ReadTimeStrategy{
    @Override
    public int calculateReadTime() {
        return 0;
    }
}
