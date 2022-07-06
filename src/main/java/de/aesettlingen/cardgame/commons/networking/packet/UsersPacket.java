package de.aesettlingen.cardgame.commons.networking.packet;

import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.event.defaultevents.UserJoinedEvent;
import java.util.ArrayList;

public class UsersPacket extends AbstractPacket {

    private final ArrayList<String> users;

    public UsersPacket(String sender, ArrayList<String> users) {
        super(sender);
        this.users = users;
    }

    @Override
    public void handle(EventBus eventBus) {
        System.out.println("Calling UsersEvent!");
        eventBus.callEvent(new UserJoinedEvent(users));
    }

    @Override
    public String getSender() {
        return super.getSender();
    }

    public ArrayList<String> getUsers() {
        return users;
    }
}
