package org.tjorven.musicquiz.groups;

import org.apache.commons.lang3.RandomStringUtils;
import org.tjorven.musicquiz.exceptions.NoSuchGameException;
import org.tjorven.musicquiz.server.WebSocketService;

import java.util.ArrayList;
import java.util.HashMap;

public class GameStorage {
    private static GameStorage instance;
    private WebSocketService webSocketService;

    private HashMap<String, Game> games = new HashMap<>();

    private GameStorage() {
    }

    public HashMap<String, Game> getGames() {
        return games;
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public static GameStorage getInstance() {
        if (instance == null) {
            instance = new GameStorage();
        }
        return instance;
    }

    public String createGame() {
        String gameID = RandomStringUtils.random(8, true, true);
        games.put(gameID, new Game(gameID));
        return gameID;
    }

    public void addClientToGame(String userName, String gameID) {
        System.out.println("Adding " + userName + " to game " + gameID);
        for (Game game : games.values()) {
            System.out.println("Game " + game.getGameID());
        }
        if (games.containsKey(gameID)) {
            games.get(gameID).addMember(new Player(userName));
        } else {
            throw new NoSuchGameException("Game \"" + gameID + "\" does not exist");
        }
    }

    public void setWebSocketService(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

}
