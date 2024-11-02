package org.tjorven.musicquiz.groups;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> members;
    private final String gameID;


    public Game(String groupID) {
        this.gameID = groupID;
        members = new ArrayList<>();
    }

    public List<Player> getMembers() {
        return members;
    }

    public void addMember(Player member) {
        members.add(member);
    }

    public void removeMember(Player member) {
        members.remove(member);
    }

    public String getGameID() {
        return gameID;
    }

}
