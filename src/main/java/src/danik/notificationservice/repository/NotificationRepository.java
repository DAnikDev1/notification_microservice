package src.danik.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import src.danik.notificationservice.entity.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> getNotificationsByUserId(Long userId);

    List<Notification> getNotificationsByUserIdAndReadStatusIsFalse(Long userId);

    @Modifying
    @Query("UPDATE Notification n SET n.readStatus = true WHERE n.userId = :userId AND n.readStatus = false")
    void readAllNotificationsForUserId(Long userId);

    @Modifying
    @Query("UPDATE Notification n SET n.readStatus = true WHERE n.id = :id")
    void readOneNotificationById(Long id);

    void removeNotificationsByUserId(Long userId);
}
