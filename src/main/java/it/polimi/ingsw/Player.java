package it.polimi.ingsw;

import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;

import java.util.ArrayList;

/**
 * Represents the class used when a player wants to play against Lorenzo il Magnifico.
 */

public class Player {
    private String username;
    private int faithMarker;
    private int position;
    private int totalVictoryPoint;
    ArrayList<LeaderCard> leaderCards;


    public Player(String username, int faithMarker, int position, int totalVictoryPoint, ArrayList<LeaderCard> leaderCards) {
        this.username = username;
        this.faithMarker = faithMarker;
        this.position = position;
        this.totalVictoryPoint = totalVictoryPoint;
        this.leaderCards = leaderCards;
    }

    public void endTurn(){

    }

    public String getUsername() {
        return username;
    }

    public int getPosition() {
        return position;
    }

    public int getFaithMarker() {
        return faithMarker;
    }

    public int getTotalVictoryPoint() {
        return totalVictoryPoint;
    }

    public int increaseFaithMarker(int amount){
        return faithMarker+amount;
    }
}
