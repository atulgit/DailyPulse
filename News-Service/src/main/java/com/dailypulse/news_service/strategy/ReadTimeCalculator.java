package com.dailypulse.news_service.strategy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * The context class for our strategy pattern.
 * It holds a reference to a specific ReadTimeStrategy and delegates the calculation to it.
 * We use the @Qualifier annotation to specify which strategy to inject.
 */

@Component
public class ReadTimeCalculator {
    private final ReadTimeStrategy readTimeStrategy;

    @Autowired
    public ReadTimeCalculator(@Qualifier("basicReadTimeStrategy") ReadTimeStrategy readTimeStrategy) {
        this.readTimeStrategy = readTimeStrategy;
    }

    /**
     * Delegates the read time calculation to the injected strategy.
     * @param content The content to calculate read time for.
     * @return The estimated read time in minutes.
     */
    public int calculateReadTime() {
        return this.readTimeStrategy.calculateReadTime();
    }
}
