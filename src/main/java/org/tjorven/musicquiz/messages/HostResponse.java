package org.tjorven.musicquiz.messages;

public class HostResponse extends ConnectionMessage {

    public HostResponse(String userName, String gameID, String role, String errorMessage) {
        super(userName, gameID, role, errorMessage);
    }
}
