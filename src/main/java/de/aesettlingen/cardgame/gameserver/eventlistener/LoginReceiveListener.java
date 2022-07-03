package de.aesettlingen.cardgame.gameserver.eventlistener;

import de.aesettlingen.cardgame.commons.event.EventHandler;
import de.aesettlingen.cardgame.commons.event.EventListener;
import de.aesettlingen.cardgame.commons.event.defaultevents.LoginEvent;
import de.aesettlingen.cardgame.gameserver.CardGameServer;

public class LoginReceiveListener implements EventListener {

    private CardGameServer cardGameServer;

    public LoginReceiveListener(CardGameServer cardGameServer) {
        this.cardGameServer = cardGameServer;
    }

    @EventHandler
    public void onLogin(LoginEvent event) {
        System.out.println("A new client joined! Welcome, " + event.getSender()+ "!");
        this.cardGameServer.addPlayer(event.getSender());
    }
}
