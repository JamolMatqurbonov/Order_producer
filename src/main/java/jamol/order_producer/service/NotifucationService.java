package jamol.order_producer.service;

import jamol.order_producer.entity.Notification;
import jamol.order_producer.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NotifucationService {

private final NotificationRepository notificationRepository;

public void createNotification(String message) {
    Notification notification = new Notification();
    notification.setMessage(message);
    notificationRepository.save(notification);
}

public List<Notification> getAllNotifications() {
    return notificationRepository.findAll();
}




}
