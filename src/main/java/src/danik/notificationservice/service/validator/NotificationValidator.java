package src.danik.notificationservice.service.validator;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import src.danik.notificationservice.repository.NotificationRepository;
import src.danik.notificationservice.service.user.UserService;

@Component
@RequiredArgsConstructor
public class NotificationValidator {
    private final UserService userService;
    private final NotificationRepository notificationRepository;

    public void checkThatUserIsExist(Long userId) {
        if (!userService.isUserExist(userId)) {
            throw new EntityNotFoundException("User doesn't exists");
        }
    }
    public void checkThatNotificationIsExist(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new EntityNotFoundException("Notification doesn't exists");
        }
    }
}
