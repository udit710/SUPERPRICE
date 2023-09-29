package au.edu.rmit.sept.superprice.service;

import au.edu.rmit.sept.superprice.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import au.edu.rmit.sept.superprice.repository.NotificationRepository;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Integer Id) {
        return notificationRepository.findByNotificationId(Id);
    }


    public Notification saveNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Integer id) {
        return notificationRepository.notificationSeen(id);
    }

    public void deleteNotification(Integer id) {
        notificationRepository.deleteById(id);
    }
}
