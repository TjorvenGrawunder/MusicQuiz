package org.tjorven.musicquiz.groups;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> members;
    private final String groupID;

    public Game(String groupID) {
        this.groupID = groupID;
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

    public String getGroupID() {
        return groupID;
    }
}
