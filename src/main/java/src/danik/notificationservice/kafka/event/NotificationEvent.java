package src.danik.notificationservice.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import src.danik.notificationservice.types.NotificationType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NotificationEvent {
    private Long eventId;
    private Long userId;
    private String text;
    private NotificationType notificationType;
}
