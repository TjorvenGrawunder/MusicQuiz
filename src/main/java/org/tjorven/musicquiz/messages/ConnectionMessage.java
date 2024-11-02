package org.tjorven.musicquiz.messages;

public class ConnectionMessage {
    private String userName;
    private String gameID;

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
