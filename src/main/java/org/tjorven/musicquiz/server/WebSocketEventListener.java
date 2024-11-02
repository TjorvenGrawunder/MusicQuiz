package org.tjorven.musicquiz.server;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.tjorven.musicquiz.groups.Game;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class WebSocketEventListener {

    private final WebSocketService webSocketService;

    // Thread-sichere Map zur Speicherung verbundener Clients
    private static final ConcurrentMap<String, String> connectedClients = new ConcurrentHashMap<>();

    public WebSocketEventListener(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    // Client-Verbindung erfassen
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        String sessionId = event.getMessage().getHeaders().get("simpSessionId").toString();
        connectedClients.put(sessionId, "connected");
        webSocketService.notifyAllClients("Neuer Client verbunden: " + sessionId);
    }

    // Client-Trennung erfassen
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        connectedClients.remove(sessionId);
        webSocketService.notifyAllClients("Client getrennt: " + sessionId);
    }



    // Alle verbundenen Clients abrufen
    public static ConcurrentMap<String, String> getConnectedClients() {
        return connectedClients;
    }
}

