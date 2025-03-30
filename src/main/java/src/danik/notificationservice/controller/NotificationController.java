package src.danik.notificationservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import src.danik.notificationservice.dto.NotificationDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import src.danik.notificationservice.service.consumer.NotificationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<NotificationDto> getAllNotificationForUserById(@Valid @PathVariable Long userId) {
        return notificationService.getAllNotificationForUserById(userId);
    }

    @GetMapping("/unread/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<NotificationDto> getUnreadNotificationsForUserById(@Valid @PathVariable Long userId) {
        return notificationService.getUnreadNotificationsForUserById(userId);
    }

    @PatchMapping("/read_all/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void readAllNotificationsForUserId(@Valid @PathVariable Long userId) {
        notificationService.readAllNotificationsForUserId(userId);
    }

    @PatchMapping("/read_one/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void readOneNotificationById(@Valid @PathVariable Long id) {
        notificationService.readOneNotificationById(id);
    }

    @DeleteMapping("/remove_one/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOneNotificationById(@Valid @PathVariable Long id) {
        notificationService.removeOneNotificationById(id);
    }

    @DeleteMapping("/remove_all/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllNotificationsForUserId(@Valid @PathVariable Long userId) {
        notificationService.removeAllNotificationsForUserId(userId);
    }
}
