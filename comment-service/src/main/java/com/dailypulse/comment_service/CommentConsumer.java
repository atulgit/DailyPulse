package com.dailypulse.comment_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CommentConsumer {
    private static final Logger logger = LoggerFactory.getLogger(CommentConsumer.class);

    private final CommentRepository commentRepository;

    @Autowired
    public CommentConsumer(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    @RabbitListener(queues = {RabbitMQConfig.CREATE_QUEUE_NAME, RabbitMQConfig.UPDATE_QUEUE_NAME})
    public void commentListener(Comment comment) {
        commentRepository.save(comment);
    }
}
