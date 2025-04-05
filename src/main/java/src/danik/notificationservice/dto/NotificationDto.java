package src.danik.notificationservice.dto;

import lombok.Builder;
import lombok.Data;
import src.danik.notificationservice.types.NotificationType;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationDto {
    private Long id;
    private boolean readStatus;
    private String text;
    private NotificationType notificationType;
    private LocalDateTime createdAt;
}
