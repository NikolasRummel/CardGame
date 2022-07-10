package de.aesettlingen.cardgame.commons.networking.packet;

import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.event.defaultevents.UserJoinedEvent;
import de.aesettlingen.cardgame.gameclient.CardGameClient;
import de.aesettlingen.cardgame.logic.card.Card;
import java.util.ArrayList;

public class UsersPacket extends AbstractPacket {

    private final ArrayList<String> users;

    public UsersPacket(String sender, ArrayList<String> users) {
        super(sender);
        this.users = users;
    }

    @Override
    public void handle(EventBus eventBus) {
        System.out.println("Hardcoded in ClientPacketListener!");

    }

    @Override
    public String getSender() {
        return super.getSender();
    }

    public ArrayList<String> getUsers() {
        return users;
    }
}
