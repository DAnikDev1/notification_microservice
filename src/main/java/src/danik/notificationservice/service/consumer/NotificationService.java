package src.danik.notificationservice.service.consumer;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.danik.notificationservice.dto.NotificationDto;
import src.danik.notificationservice.dto.NotificationEvent;
import src.danik.notificationservice.entity.Notification;
import src.danik.notificationservice.mapper.NotificationMapper;
import src.danik.notificationservice.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Transactional
    public void processNotification(NotificationEvent event) {
        Notification notification = notificationMapper.eventToEntity(event);
        notificationRepository.save(notification);
    }

    @Transactional
    public List<NotificationDto> getAllNotificationForUserById(Long userId) {
        return notificationRepository.getNotificationsByUserId(userId).stream().map(notificationMapper::toDto).toList();
    }

    @Transactional
    public List<NotificationDto> getUnreadNotificationsForUserById(Long userId) {
        return notificationRepository.getNotificationsByUserIdAndReadIsFalse(userId).stream().map(notificationMapper::toDto).toList();
    }

    @Transactional
    public void readAllNotificationsForUserId(Long userId) {
        notificationRepository.readAllNotificationsForUserId(userId);
    }

    @Transactional
    public void readOneNotificationById(Long id) {
        notificationRepository.readOneNotificationById(id);
    }

    @Transactional
    public void removeOneNotificationById(@Valid Long id) {
        notificationRepository.deleteById(id);
    }

    @Transactional
    public void removeAllNotificationsForUserId(@Valid Long userId) {
        notificationRepository.removeNotificationsByUserId(userId);
    }
}
