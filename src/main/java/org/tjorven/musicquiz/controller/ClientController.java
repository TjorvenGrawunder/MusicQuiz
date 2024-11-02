package org.tjorven.musicquiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tjorven.musicquiz.server.WebSocketEventListener;
import org.tjorven.musicquiz.server.WebSocketService;

import java.util.Set;

@RestController
public class ClientController {

    private final WebSocketService webSocketService;

    @Autowired
    public ClientController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    // Endpunkt zum Abrufen aller verbundenen Clients
    @GetMapping("/connected-clients")
    public Set<String> getConnectedClients() {
        return WebSocketEventListener.getConnectedClients().keySet();
    }

    // Nachricht an alle verbundenen Clients senden
    @GetMapping("/notify-all")
    public String notifyAllClients() {
        webSocketService.notifyAllClients("Aktualisierung für alle verbundenen Clients!");
        return "Nachricht an alle Clients gesendet";
    }
}

