package src.danik.notificationservice.dto;

import lombok.Builder;
import lombok.Data;
import src.danik.notificationservice.types.NotificationType;

@Data
@Builder
public class NotificationEvent {
    private String eventId;
    private Long userId;
    private String text;
    private NotificationType notificationType;
}
