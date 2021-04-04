package it.polimi.ingsw;

import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Storage.Deposit;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Exceptions.EmptyDeposit;

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
    private ArrayList<Resource> resourceBuffer;
    private Board board;

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
        resourceBuffer = new ArrayList<>();
        board = new Board();
    }


    /**
     * Method getUsername returns the username of this Player object.

     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Method getPosition returns the position of the Player's turn.

     */
    public int getPosition() {
        return position;
    }

    /**
     * Method getFaithMarker returns the Faith Marker of this Player object.
     */
    public int getFaithMarker() {
        return faithMarker;
    }

    /**
     * Method getTotalVictoryPoint returns the amount of Player's victory points.

     */
    public int getTotalVictoryPoint() {
        return totalVictoryPoint;
    }

    /**
     * Method addLeaderCard creates four instances of Leader Cards related to the card received from the deck.
     *  @param card of type Card - the card chosen by the user.
     *
     */
    public void addLeaderCard(LeaderCard card) {

    }


    /**
     * Method increaseFaithMarker moves forward the faith marker by one position
     */
    public void increaseFaithMarker(){
        faithMarker += 1;
    }

    public void fillBuffer (int position){
        try {
            resourceBuffer.add(board.getDeposits().get(position).takeResource());
        } catch (EmptyDeposit emptyDeposit) {
            emptyDeposit.printStackTrace();
        }
    }

    public boolean endTurn(){
        return true;
    }
}
