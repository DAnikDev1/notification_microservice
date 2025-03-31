package src.danik.notificationservice.service.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import src.danik.notificationservice.dto.NotificationEvent;
import src.danik.notificationservice.service.notification.NotificationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationService notificationService;

    @KafkaListener(topics = "${kafka.topic.notification}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(NotificationEvent notification) {
        log.info("Received new notification: {}", notification);
        notificationService.processNotification(notification);
    }
}
