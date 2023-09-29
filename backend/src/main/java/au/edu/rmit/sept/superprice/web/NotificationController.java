package au.edu.rmit.sept.superprice.web;
import au.edu.rmit.sept.superprice.model.Notification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import au.edu.rmit.sept.superprice.model.User;
import au.edu.rmit.sept.superprice.service.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    @GetMapping("/{id}")
    public Notification getNotificationById(@PathVariable Integer id) {
        return notificationService.getNotificationById(id);
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification Notification) {
        return notificationService.saveNotification(Notification);
    }

    @PutMapping("/{id}")
    public Notification updateNotification(@PathVariable Integer id, @RequestBody Notification notification) {
        // Logic to update the notification based on the provided ID
        return notificationService.updateNotification(id);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Integer id) {
        notificationService.deleteNotification(id);
    }
}