package de.aesettlingen.cardgame.gameclient.gui;

import de.aesettlingen.cardgame.gameclient.client_facades.ChatFacade;
import de.aesettlingen.cardgame.gameclient.client_facades.GameFacade;
import de.aesettlingen.cardgame.gameclient.gui.chatgui.ChatPanel;
import de.aesettlingen.cardgame.gameclient.gui.game_gui.GamePanel;
import de.aesettlingen.cardgame.gameclient.gui.waiting_screen.StartMethod;
import de.aesettlingen.cardgame.gameclient.gui.waiting_screen.WaitingScreenPanel;
import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.card.CardHand;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauMove;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauPlayer;

import javax.swing.*;
import java.awt.*;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class MainFrame extends ColoredFrame {

    private WaitingScreenPanel waitingScreenPanel;
    private ChatPanel chatPanel;
    private GamePanel gamePanel;
    private JPanel mainPanel;

    private final GameFacade gameFacade;
    private final ChatFacade chatFacade;

    public MainFrame(GameFacade gameFacade, ChatFacade chatFacade) {
        this.gameFacade = gameFacade;
        this.chatFacade = chatFacade;
        initGuiElements();
    }

    private void initGuiElements() {
        this.waitingScreenPanel = new WaitingScreenPanel(new StartMethod() {
            @Override
            public void start() {
                System.out.println("start game");
            }
        });

        this.chatPanel = new ChatPanel(chatFacade);
        this.gamePanel = new GamePanel(gameFacade);

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());

        setContentOfMainFrame(waitingScreenPanel);


        super.setContentPane(mainPanel);
        super.setTitle("CardGame - Waiting");

        super.setSize(new Dimension(1280, 720));
        super.setResizable(false);
        this.setVisible(true);
    }

    public void switchToGame() {
        setContentOfMainFrame(gamePanel);
    }

    public void switchToWaitingScreen() {
        setContentOfMainFrame(waitingScreenPanel);
    }

    private void setContentOfMainFrame(JComponent component) {

        this.mainPanel.removeAll();
        this.mainPanel.add(chatPanel, BorderLayout.EAST);
        this.mainPanel.add(component, BorderLayout.CENTER);
    }

    public WaitingScreenPanel getWaitingScreenPanel() {
        return waitingScreenPanel;
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public static void main(String[] args) {

        String playerName = "Max Musterman";

        MainFrame mainFrame = new MainFrame(new GameFacade() {
                @Override
                public MauMauPlayer getPlayer() {
                    return new MauMauPlayer(
                                playerName,
                                new CardHand(
                                        new Card("7", "clubs"),
                                        new Card("8", "clubs"),
                                        new Card("9", "clubs"),
                                        new Card("10", "clubs"),
                                        new Card("jack", "clubs"),
                                        new Card("queen", "clubs"),
                                        new Card("king", "clubs"),
                                        new Card("7", "diamonds"),
                                        new Card("8", "diamonds"),
                                        new Card("9", "diamonds"),
                                        new Card("10", "diamonds"),
                                        new Card("jack", "diamonds"),
                                        new Card("queen", "diamonds"),
                                        new Card("king", "diamonds")
                                )
                            );
                }

                @Override
                public Card getTopCard() {
                    return new Card("jack", "spades");
                }

            @Override
            public void sendCardOfMove(Card card) {
                MauMauMove move = new MauMauMove(playerName, card);
                System.out.println( playerName + (move.isRaisingACard()? " raises a card" : " plays a "+move.getCard().getName()+ " " + move.getCard().getColor()));
            }
        },
            new ChatFacade() {
                @Override
                public void sendMessage(String text) {
                    System.out.println("send message: " + text);
                }
            }
        );
        mainFrame.switchToGame();
    }
}