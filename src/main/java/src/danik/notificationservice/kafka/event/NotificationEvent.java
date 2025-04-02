package src.danik.notificationservice.kafka.event;

import lombok.Builder;
import lombok.Data;
import src.danik.notificationservice.types.NotificationType;

@Data
@Builder
public class NotificationEvent {
    private Long eventId;
    private Long userId;
    private String text;
    private NotificationType notificationType;
}
