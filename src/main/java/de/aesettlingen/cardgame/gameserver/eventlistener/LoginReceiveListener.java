package de.aesettlingen.cardgame.gameserver.eventlistener;

import de.aesettlingen.cardgame.commons.event.EventHandler;
import de.aesettlingen.cardgame.commons.event.EventListener;
import de.aesettlingen.cardgame.commons.event.defaultevents.LoginEvent;

public class LoginReceiveListener implements EventListener {

    @EventHandler
    public void onLogin(LoginEvent event) {
        System.out.println("A new client joined! Welcome, " + event.getSender()+ "!");
    }
}
