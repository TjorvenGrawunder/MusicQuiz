package org.tjorven.musicquiz.controller;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.tjorven.musicquiz.groups.GameStorage;
import org.tjorven.musicquiz.messages.ConnectionMessage;
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
    public String handleGetGroupButtonPress(@Header("simpSessionId") String sessionId, ConnectionMessage message) {
        System.out.println("Client " + message.getUserName() + " hat Gruppe " + message.getGameID() + " angefordert");
        GameStorage.getInstance().addClientToGame(message.getUserName(), message.getGameID());
        return "Gruppe: " + message.getGameID() + " hinzugefügt";
    }
}
