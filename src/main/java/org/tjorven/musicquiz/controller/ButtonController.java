package org.tjorven.musicquiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.tjorven.musicquiz.exceptions.NoSuchGameException;
import org.tjorven.musicquiz.groups.GameStorage;
import org.tjorven.musicquiz.messages.ConnectionMessage;
import org.tjorven.musicquiz.messages.HostResponse;
import org.tjorven.musicquiz.messages.JoinResponse;
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

    @MessageMapping("/join-game-click")
    @SendTo("/topic/join-game-response")
    public JoinResponse handleJoinGameButtonPress(@Header("simpSessionId") String sessionId, ConnectionMessage message) {
        try {
            System.out.println("Client " + message.getUserName() + " hat Game " + message.getGameID() + " angefordert");
            GameStorage.getInstance().addClientToGame(message.getUserName(), message.getGameID());
            return new JoinResponse(message.getUserName(), message.getGameID(), "player", null);
        } catch (NoSuchGameException e) {
            return new JoinResponse(null, null, null, e.getMessage());
        }

    }

    @MessageMapping("/host-game-click")
    @SendTo("/topic/host-game-response")
    public HostResponse handleHostGameButtonPress(@Header("simpSessionId") String sessionId, ConnectionMessage message) {
        String gameID = GameStorage.getInstance().createGame();
        System.out.println("Client " + message.getUserName() + " hat Game " + gameID + " erstellt");
        return new HostResponse(message.getUserName(), gameID, "host", null);
    }
}
