package it.polimi.ingsw;

import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ConcreteStrategyResource;
import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Exceptions.EmptyDeposit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Player class represents the user and player of the board game.
 *
 * @author Filippo Caliò
 */

public class Player {
    private final String username;
    private int position;
    private int totalVictoryPoint;
    private ArrayList<LeaderCard> leaderCards;
    private ArrayList<Resource> resourceBuffer;
    private Board board;
    private Game game;

    /**
     * Constructor Player creates a new Player instance.
     *
     * @param username of type String.
     * @param position of type int - the user turn's position.
     */
    public Player(String username, int position, Game game) {
        this.username = username;
        totalVictoryPoint = 0;
        leaderCards = new ArrayList<>();
        resourceBuffer = new ArrayList<>();
        board = new Board();
        this.game = game;
    }

    public ArrayList<Resource> getResourceBuffer() {
        return resourceBuffer;
    }

    public Board getBoard() {
        return board;
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
     * Method getTotalVictoryPoint returns the amount of Player's victory points.
     */
    public int getTotalVictoryPoint() {
        return totalVictoryPoint;
    }

    /**
     * Method getLeaderCards returns the leader cards of this Player object.
     */
    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    /**
     * Method addLeaderCard creates four instances of Leader Cards related to the card received from the deck.
     *  @param card of type Card - the card chosen by the user.
     */
    public void addLeaderCard(LeaderCard card) {
        leaderCards.add(card);
    }


    /**
     * Method chooseResourcesBeginningGame adds to the player's resource buffer array the parameter resource
     * at the beginning of the game according to the player's position
     * @see Game
     */
    public void chooseResourcesBeginningGame(int amount){
        Resource[] availableResource = new Resource[4];
        Scanner input = new Scanner (System.in);

        availableResource[0] = new Resource(ResourceType.COIN);
        availableResource[1] = new Resource(ResourceType.SERVANT);
        availableResource[2] = new Resource(ResourceType.SHIELD);
        availableResource[3] = new Resource(ResourceType.STONE);

        for (int i = 0; i < amount; i++) {
            System.out.println("Which resource do you choose?");
            int choice = input.nextInt();
            resourceBuffer.add(availableResource[choice]);
        }
    }

    // TODO: 05/04/2021 scrivere javadoc
    public void fillBuffer (int position){
        try {
            resourceBuffer.add(board.getDeposits().get(position).takeResource());
        } catch (EmptyDeposit emptyDeposit) {
            emptyDeposit.printStackTrace();
        }
    }

    /**
     * Method endTurn called when a player wants to end his own turn
     */
    public boolean endTurn(){
        return true;
    }
}
