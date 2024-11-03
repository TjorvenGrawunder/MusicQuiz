package org.tjorven.musicquiz.messages;

public class JoinResponse extends ConnectionMessage {

    public JoinResponse(String userName, String gameID, String role, String errorMessage) {
        super(userName, gameID, role, errorMessage);
    }
}
