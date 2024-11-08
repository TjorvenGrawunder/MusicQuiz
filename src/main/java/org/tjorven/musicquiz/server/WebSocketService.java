package org.tjorven.musicquiz.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.tjorven.musicquiz.groups.Game;
import org.tjorven.musicquiz.groups.GameStorage;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        GameStorage.getInstance().setWebSocketService(this);
    }

    // Nachricht an alle verbundenen Clients senden
    public void notifyAllClients(String message) {
        messagingTemplate.convertAndSend("/topic/updates", message);
    }

    // Nachricht an alle Clients eines Games
    public void notifyGameClients(Game game, String message) {
        messagingTemplate.convertAndSend("/topic/" + game.getGameID(), message);
    }

    public void notifyForPlayerUpdate(Game game) {
        String playersPanelHTML = game.generatePlayersPanelHTML();
        messagingTemplate.convertAndSend("/topic/player-update/" + game.getGameID(), playersPanelHTML);
    }
}

