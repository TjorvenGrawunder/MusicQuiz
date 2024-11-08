package org.tjorven.musicquiz.messages;

public class BuzzerMessage {
    private String userName;
    private String gameID;

    public BuzzerMessage(String userName, String gameID) {
        this.userName = userName;
        this.gameID = gameID;
    }

    public String getUserName() {
        return userName;
    }

    public String getGameID() {
        return gameID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }
}
