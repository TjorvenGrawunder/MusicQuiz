package org.tjorven.musicquiz.server;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class WebSocketEventListener {

    private final SimpMessagingTemplate messagingTemplate;

    // Thread-sichere Map zur Speicherung verbundener Clients
    private static final ConcurrentMap<String, String> connectedClients = new ConcurrentHashMap<>();

    public WebSocketEventListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Client-Verbindung erfassen
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        String sessionId = event.getMessage().getHeaders().get("simpSessionId").toString();
        connectedClients.put(sessionId, "verbunden");
        System.out.println("Client verbunden: " + sessionId);
    }

    // Client-Trennung erfassen
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        connectedClients.remove(sessionId);
        System.out.println("Client getrennt: " + sessionId);
    }

    // Nachricht an alle verbundenen Clients senden
    public void notifyAllClients(String message) {
        messagingTemplate.convertAndSend("/topic/updates", message);
    }

    // Alle verbundenen Clients abrufen
    public static ConcurrentMap<String, String> getConnectedClients() {
        return connectedClients;
    }
}

