package org.tjorven.musicquiz.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ButtonController {

    @MessageMapping("/button-click")
    @SendTo("/topic/updates")
    public String handleButtonPress() {
        return "Spieler hat auf den Button gedrückt!";
    }
}
