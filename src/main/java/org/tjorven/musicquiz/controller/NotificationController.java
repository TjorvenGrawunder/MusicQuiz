package org.tjorven.musicquiz.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/notify")
    public void notifySpecificClient(@Header("simpSessionId") String sessionId, String message) {
        // Nachricht nur an den Client zurücksenden, der den Knopf gedrückt hat
        messagingTemplate.convertAndSendToUser(sessionId, "/topic/private", "Deine Nachricht: " + message);
    }
}

