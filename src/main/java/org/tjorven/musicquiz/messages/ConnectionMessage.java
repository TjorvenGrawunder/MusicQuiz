package org.tjorven.musicquiz.messages;

public class ConnectionMessage {
    private String userName;
    private String gameID;
    private String role;
    private String errorMessage;

    public ConnectionMessage(String userName, String gameID, String role, String errorMessage) {
        this.userName = userName;
        this.gameID = gameID;
        this.role = role;
        this.errorMessage = errorMessage;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
