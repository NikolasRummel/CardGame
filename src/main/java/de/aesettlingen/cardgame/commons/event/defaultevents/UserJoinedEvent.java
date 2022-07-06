package de.aesettlingen.cardgame.commons.event.defaultevents;

import de.aesettlingen.cardgame.commons.event.Event;
import java.util.ArrayList;

public class UserJoinedEvent extends Event {

    private final ArrayList<String> users;

    public UserJoinedEvent(ArrayList<String> users) {
        this.users = users;
    }

    public ArrayList<String> getUsers() {
        return users;
    }
}