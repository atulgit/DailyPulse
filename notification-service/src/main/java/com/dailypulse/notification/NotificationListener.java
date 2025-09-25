package com.dailypulse.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);

//    private static final Logger log = LoggerFactory.getLogger(NotificationListener.class);

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void handlePublishedArticles() {
        System.out.println("Notification Service: Received message for a published article: " + message);

        log.warn("");
        // In a production environment, this is where you would integrate with an external service,
        // such as a push notification provider, email service, or social media API.
        System.out.println("Notification Service: Sending a push notification and email alert to all subscribers...");
        // This process is now completely decoupled from the main application.
    }
}
