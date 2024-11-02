package org.tjorven.musicquiz.groups;

import java.util.ArrayList;
import java.util.HashMap;

public class GameStorage {
    private static GameStorage instance;

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

    public void addClientToGame(String sessionId, String gameID) {
        if (games.containsKey(gameID)) {
            games.get(sessionId).addMember(new Player(sessionId));
        } else {
            games.put(sessionId, new Game(gameID));
            games.get(sessionId).addMember(new Player(sessionId));
        }
    }
}
