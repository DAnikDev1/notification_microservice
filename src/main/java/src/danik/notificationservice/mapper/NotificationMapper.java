package src.danik.notificationservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import src.danik.notificationservice.dto.NotificationDto;
import src.danik.notificationservice.dto.NotificationEvent;
import src.danik.notificationservice.entity.Notification;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {
    Notification eventToEntity(NotificationEvent notificationEvent);

    NotificationEvent eventToDto(Notification notification);

    Notification toEntity(NotificationDto notificationDto);

    NotificationDto toDto(Notification notification);
}
