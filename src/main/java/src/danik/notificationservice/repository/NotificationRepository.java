package src.danik.notificationservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import src.danik.notificationservice.dto.NotificationDto;
import src.danik.notificationservice.entity.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> getNotificationsByUserId(Long userId);

    List<Notification> getNotificationsByUserIdAndReadIsFalse(Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isRead = true WHERE n.userId = :userId AND n.isRead = false")
    void readAllNotificationsForUserId(Long userId);
}
