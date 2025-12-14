
package com.smarttravel.notificationservice.service.impl;

import com.smarttravel.notificationservice.dto.NotificationRequestDTO;
import com.smarttravel.notificationservice.entity.Notification;
import com.smarttravel.notificationservice.repository.NotificationRepository;
import com.smarttravel.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationRequestDTO request) {
        Notification notification = Notification.builder()
                .userId(request.getUserId())
                .subject(request.getSubject())
                .message(request.getMessage())
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);

        log.info("Notification sent to user {}: {}", request.getUserId(), request.getSubject());
    }
}
