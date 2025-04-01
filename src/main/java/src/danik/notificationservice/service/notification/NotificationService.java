package src.danik.notificationservice.service.notification;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.danik.notificationservice.dto.NotificationDto;
import src.danik.notificationservice.kafka.event.NotificationEvent;
import src.danik.notificationservice.entity.Notification;
import src.danik.notificationservice.mapper.NotificationMapper;
import src.danik.notificationservice.repository.NotificationRepository;
import src.danik.notificationservice.service.validator.NotificationValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final NotificationValidator notificationValidator;

    @Transactional
    public void processNotification(NotificationEvent event) {
        Notification notification = notificationMapper.eventToEntity(event);
        notificationRepository.save(notification);
    }

    @Transactional(readOnly = true)
    public List<NotificationDto> getAllNotificationForUserById(Long userId) {
        notificationValidator.checkThatUserIsExist(userId);
        return notificationRepository.getNotificationsByUserId(userId).stream().map(notificationMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<NotificationDto> getUnreadNotificationsForUserById(Long userId) {
        notificationValidator.checkThatUserIsExist(userId);
        return notificationRepository.getNotificationsByUserIdAndReadIsFalse(userId).stream().map(notificationMapper::toDto).toList();
    }

    @Transactional
    public void readAllNotificationsForUserId(Long userId) {
        notificationValidator.checkThatUserIsExist(userId);
        notificationRepository.readAllNotificationsForUserId(userId);
    }

    @Transactional
    public void readOneNotificationById(Long id) {
        notificationValidator.checkThatNotificationIsExist(id);
        notificationRepository.readOneNotificationById(id);
    }

    @Transactional
    public void removeOneNotificationById(Long id) {
        notificationRepository.deleteById(id);
    }

    @Transactional
    public void removeAllNotificationsForUserId(Long userId) {
        notificationValidator.checkThatUserIsExist(userId);
        notificationRepository.removeNotificationsByUserId(userId);
    }
}
