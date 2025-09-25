package com.dailypulse.comment_service;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String CREATE_QUEUE_NAME = "comment-created-queue";
    public static final String UPDATE_QUEUE_NAME = "commend-updated-queue";

    public static final String TOPIC_EXCHANGE_NAME = "comment-exchange";
    public static final String CREATE_ROUTING_KEY = "comment.created";
    public static final String UPDATE_ROUTING_KEY = "comment.updated";


    @Bean
    public Queue updatedCommentQueue() {
        return new Queue(UPDATE_QUEUE_NAME);
    }

    public Queue createdCommentQueue() {
        return new Queue(CREATE_QUEUE_NAME);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    //Bind the created comment queue to exchange with created comment routing key.
    @Bean
    public Binding createdCommentBinding() {
        return BindingBuilder.bind(createdCommentQueue()).to(exchange()).with(CREATE_ROUTING_KEY);
    }

    //Bind the updated to comment queue to exchange with updated routing key.
    @Bean
    public Binding updatedCommentBinding() {
        return BindingBuilder.bind(updatedCommentQueue()).to(exchange()).with(UPDATE_ROUTING_KEY);
    }
}
