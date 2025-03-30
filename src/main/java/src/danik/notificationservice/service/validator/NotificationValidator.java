package src.danik.notificationservice.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import src.danik.notificationservice.service.user.UserService;

@Component
@RequiredArgsConstructor
public class NotificationValidator {
    private final UserService userService;


}
