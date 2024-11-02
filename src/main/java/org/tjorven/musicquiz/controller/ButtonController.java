package org.tjorven.musicquiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.tjorven.musicquiz.groups.GameStorage;
import org.tjorven.musicquiz.messages.ConnectionMessage;
import org.tjorven.musicquiz.server.WebSocketEventListener;
import org.tjorven.musicquiz.server.WebSocketService;

@Controller
public class ButtonController {

    private final WebSocketService webSocketService;

    @Autowired
    public ButtonController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @MessageMapping("/button-click")
    @SendTo("/topic/updates")
    public String handleButtonPress() {
        StringBuilder message = new StringBuilder("Verbundene Clients: \n");

        for (String client : WebSocketEventListener.getConnectedClients().keySet()) {
            message.append(client).append("\n");
        }

        return message.toString();
    }

    @MessageMapping("/buzzer-click")
    public void handleBuzzerPress(@Header("simpSessionId") String sessionId, String userName) {
        webSocketService.notifyGameClients(GameStorage.getInstance().getGames().get(userName), "Buzzer gedrückt von " + userName);
    }

    @MessageMapping("/get-game-click")
    @SendTo("/topic/updates")
    public String handleGetGroupButtonPress(@Header("simpSessionId") String sessionId, ConnectionMessage message) {
        System.out.println("Client " + message.getUserName() + " hat Gruppe " + message.getGameID() + " angefordert");
        GameStorage.getInstance().addClientToGame(message.getUserName(), message.getGameID());
        return "";
    }
}
