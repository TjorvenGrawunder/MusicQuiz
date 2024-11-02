package org.tjorven.musicquiz.controller;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.tjorven.musicquiz.groups.GameStorage;
import org.tjorven.musicquiz.server.WebSocketEventListener;

@Controller
public class ButtonController {

    @MessageMapping("/button-click")
    @SendTo("/topic/updates")
    public String handleButtonPress() {
        StringBuilder message = new StringBuilder("Verbundene Clients: \n");

        for (String client : WebSocketEventListener.getConnectedClients().keySet()) {
            message.append(client).append("\n");
        }

        return message.toString();
    }

    @MessageMapping("/get-group-click")
    @SendTo("/topic/updates")
    public String handleGetGroupButtonPress(@Header("simpSessionId") String sessionId, String message) {
        GameStorage.getInstance().addClientToGame(sessionId, message);
        return "Gruppe 1: " + message;
    }
}
