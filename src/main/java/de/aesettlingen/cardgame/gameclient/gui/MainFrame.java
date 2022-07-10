package de.aesettlingen.cardgame.gameclient.gui;

import de.aesettlingen.cardgame.gameclient.client_facades.ChatFacade;
import de.aesettlingen.cardgame.gameclient.client_facades.MauMauFacade;
import de.aesettlingen.cardgame.gameclient.gui.chatgui.ChatPanel;
import de.aesettlingen.cardgame.gameclient.gui.game_gui.MauMauPanel;
import de.aesettlingen.cardgame.gameclient.gui.waiting_screen.StartMethod;
import de.aesettlingen.cardgame.gameclient.gui.waiting_screen.WaitingScreenPanel;
import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.card.CardHand;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauMove;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauState;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class MainFrame extends ColoredFrame {

    private WaitingScreenPanel waitingScreenPanel;
    private ChatPanel chatPanel;
    private MauMauPanel gamePanel;
    private JPanel mainPanel;

    private final MauMauFacade gameFacade;
    private final ChatFacade chatFacade;

    public MainFrame(MauMauFacade gameFacade, ChatFacade chatFacade) {
        this.chatFacade = chatFacade;
        this.gameFacade = gameFacade;
        this.initWaitingGuiElements();
    }

    public void startGame(MauMauState gameState) {
        initGameGuiElements(gameState);
    }

    private void initWaitingGuiElements() {
        this.waitingScreenPanel = new WaitingScreenPanel(new StartMethod() {
            @Override
            public void start() {
                System.out.println("start game");
            }
        });

        this.chatPanel = new ChatPanel(chatFacade);

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());

        setContentOfMainFrame(waitingScreenPanel);


        super.setContentPane(mainPanel);
        super.setTitle("CardGame - Waiting");

        super.setSize(new Dimension(1280, 720));
        super.setResizable(false);
        this.setVisible(true);
    }

    private void initGameGuiElements(MauMauState gameState) {
        this.gamePanel = new MauMauPanel(gameFacade, gameState);
        setContentOfMainFrame(gamePanel);
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

        ArrayList<String> listOfPlayerNames = new ArrayList<>();
        listOfPlayerNames.add("Paul");
        listOfPlayerNames.add(playerName);
        listOfPlayerNames.add("404");
        listOfPlayerNames.add("Ken");

        ArrayList<Integer> numberOfCards = new ArrayList<>();
        numberOfCards.add(4);
        numberOfCards.add(16);
        numberOfCards.add(4);
        numberOfCards.add(4);

        /*
            MainFrame mainFrame = new MainFrame(
              new MauMauFacade() {
                @Override
                public String getNameOfPlayer() {
                    return playerName;
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
            },
            new MauMauState (
              listOfPlayerNames.get(3),
              listOfPlayerNames,
              numberOfCards,
              new CardHand(
                new Card("7", "clubs"),
                new Card("8", "clubs"),
                new Card("9", "clubs"),
                new Card("10", "clubs"),
                new Card("jack", "clubs"),
                new Card("queen", "clubs"),
                new Card("king", "clubs"),
                new Card("ace", "clubs"),
                new Card("7", "diamonds"),
                new Card("8", "diamonds"),
                new Card("9", "diamonds"),
                new Card("10", "diamonds"),
                new Card("jack", "diamonds"),
                new Card("queen", "diamonds"),
                new Card("king", "diamonds"),
                new Card("ace", "diamonds")
              ),
              new Card("8", "spades")
            )
        );*/
       // mainFrame.switchToGame();
    }
}