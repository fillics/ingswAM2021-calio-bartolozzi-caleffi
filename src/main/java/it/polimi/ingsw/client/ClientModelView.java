package it.polimi.ingsw.client;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.marbles.MarketTray;

import java.util.ArrayList;
import java.util.LinkedList;

public class ClientModelView {
    private Player MyPlayer;
    private MarketTray marketTray;
    private ArrayList<LinkedList<DevelopmentCard>> developmentGrid;

    public Player getMyPlayer() {
        return MyPlayer;
    }

    public MarketTray getMarketTray() {
        return marketTray;
    }

    public ArrayList<LinkedList<DevelopmentCard>> getDevelopmentGrid() {
        return developmentGrid;
    }
}
