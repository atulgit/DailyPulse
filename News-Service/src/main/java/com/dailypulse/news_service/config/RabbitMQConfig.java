package com.dailypulse.news_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
Configuration class for RabbitMQ. It defines the queue, exchange and binding for message routing.
 */
@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "article.published.queue";
    public static final String EXCHANGE_NAME = "news.platform.exchange";
    public static final String ROUTING_KEY = "article.published";

    /*
    Defines the queue to hold messages.
    returning the configured queue @Bean.
     */
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    /*
    Defines a direct exchange to route the message based on key.
    returns exchange @Bean
     */
    @Bean
    public Exchange exchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME).build();
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY).noargs();
    }

    /**
     * Registers a JSON message converter.
     * @return The Jackson2JsonMessageConverter bean.
     */
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Creates a RabbitTemplate for sending messages, configured with our JSON converter.
     * @param connectionFactory The connection factory.
     * @return The configured RabbitTemplate.
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
