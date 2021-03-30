package it.polimi.ingsw;

import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;

import java.util.ArrayList;

/**
 * Player class represents the user and player of the board game.
 *
 * @author Filippo Caliò
 */

public class Player {
    private final String username;
    private int faithMarker;
    private int position;
    private int totalVictoryPoint;
    private ArrayList<LeaderCard> leaderCards;

    /**
     * Constructor Player creates a new Player instance.
     *
     * @param username of type String.
     * @param position of type int - the user turn's position.
     */
    public Player(String username, int position) {
        this.username = username;
        this.position = position;
        faithMarker = 0;
        totalVictoryPoint = 0;
        leaderCards = new ArrayList<>();
    }


    /**
     * Method getUsername returns the username of this Player object.
     *
     * @return the username (type String) of this Player object.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Method getPosition returns the position of the Player's turn.
     *
     * @return the position (type int) of this Player object.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Method getFaithMarker returns the Faith Marker of this Player object.
     *
     * @return the faith marker (type int) of this Player object.
     */
    public int getFaithMarker() {
        return faithMarker;
    }

    /**
     * Method getTotalVictoryPoint returns the amount of Player's victory points.
     *
     * @return how many victory points (type int) this Player object has.
     */
    public int getTotalVictoryPoint() {
        return totalVictoryPoint;
    }

    /**
     * Method addLeaderCard creates four instances of Leader Cards related to the card received from the deck.
     *
     * @param card of type Card - the card chosen by the user.

     */
    public void addLeaderCard(LeaderCard card) {

    }


    /**
     * Method increaseFaithMarker adds to the faith marker a specific amount, to move forward it.
     *
     * @return the new position (type int) of this Player's faith marker.
     */
    public int increaseFaithMarker(int amount){
        faithMarker += amount;
        return faithMarker;
    }

    public boolean endTurn(){
        return true;
    }
}
