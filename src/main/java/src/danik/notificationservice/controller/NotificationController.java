package src.danik.notificationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import src.danik.notificationservice.dto.NotificationDto;
import jakarta.validation.Valid;
import src.danik.notificationservice.service.notification.NotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
@Tag(name = "Notification API", description = "API for managing notifications")
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    @Operation(summary = "Get all notifications for user using userId")
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<NotificationDto> getAllNotificationForUserById(@Valid @PathVariable Long userId) {
        return notificationService.getAllNotificationForUserById(userId);
    }

    @Operation(summary = "Get all unread notifications for user using userId")
    @GetMapping("/unread/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<NotificationDto> getUnreadNotificationsForUserById(@Valid @PathVariable Long userId) {
        return notificationService.getUnreadNotificationsForUserById(userId);
    }

    @Operation(summary = "Read all notification as read for user using userId")
    @PatchMapping("/read_all/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void readAllNotificationsForUserId(@Valid @PathVariable Long userId) {
        notificationService.readAllNotificationsForUserId(userId);
    }

    @Operation(summary = "Read one notification using notification id")
    @PatchMapping("/read_one/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void readOneNotificationById(@Valid @PathVariable Long id) {
        notificationService.readOneNotificationById(id);
    }

    @Operation(summary = "Delete one notification using notification id")
    @DeleteMapping("/remove_one/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOneNotificationById(@Valid @PathVariable Long id) {
        notificationService.removeOneNotificationById(id);
    }

    @Operation(summary = "Delete all notifications using userId")
    @DeleteMapping("/remove_all/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllNotificationsForUserId(@Valid @PathVariable Long userId) {
        notificationService.removeAllNotificationsForUserId(userId);
    }
}
