package com.dailypulse.comment_service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final RabbitTemplate rabbitTemplate;

    public CommentService(CommentRepository commentRepository, RabbitTemplate rabbitTemplate) {
        this.commentRepository = commentRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void createCommentAsync(Comment comment) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.CREATE_ROUTING_KEY, comment);
    }

    public void updateCommentAsync(Comment comment) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME, RabbitMQConfig.UPDATE_ROUTING_KEY, comment);
    }

    /*
    Should be part of RabbitMQ listener/consumer service.
     */
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }
//
//    @Transactional
//    public Comment saveCommentToDb(Comment comment) {
//        return commentRepository.save(comment);
//    }
}
