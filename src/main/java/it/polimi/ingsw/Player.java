package it.polimi.ingsw;

import it.polimi.ingsw.Board.Board;

import it.polimi.ingsw.Board.Resources.Resource;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Exceptions.EmptyDeposit;
import it.polimi.ingsw.Exceptions.LeaderCardNotFound;

import java.util.ArrayList;

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
    private int chosenResource;
    private ArrayList<LeaderCard> whiteMarbleCardChoice;

    /**
     * Constructor Player creates a new Player instance.
     *
     * @param username of type String.
     * @param position of type int - the user turn's position.
     */
    public Player(String username, int position, Game game) {
        this.username = username;
        this.position = position;
        totalVictoryPoint = 0;
        leaderCards = new ArrayList<>();
        resourceBuffer = new ArrayList<>();
        whiteMarbleCardChoice= new ArrayList<>();
        board = new Board(game);
        this.game = game;
    }


    public ArrayList<LeaderCard> getWhiteMarbleCardChoice() {
        return whiteMarbleCardChoice;
    }


    public void setWhiteMarbleCardChoice(ArrayList<LeaderCard> whiteMarbleCardChoice) {
        this.whiteMarbleCardChoice= whiteMarbleCardChoice;
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
     *  @param card (type LeaderCard) - the card chosen by the user.
     */
    public void addLeaderCard(LeaderCard card) {
        leaderCards.add(card);
    }

    /**
     * Method removeLeaderCard removes from the leaderCards player's array the selected card
     * @param cardToRemove (type LeaderCard) - it is the card that the player discards
     * @throws LeaderCardNotFound if the player has not got the cardToRemove
     */
    public void removeLeaderCard(LeaderCard cardToRemove) throws LeaderCardNotFound {
        if (leaderCards.contains(cardToRemove)){
            leaderCards.remove(cardToRemove);
        }
        else throw new LeaderCardNotFound();
    }

    /**
     * Method chooseResourcesBeginningGame adds to the player's resource buffer array the parameter resource
     * at the beginning of the game according to the player's position
     * @see Game
     */
    public void addResourcesBeginningGame(int choice){
        Resource[] availableResource = new Resource[4];

        availableResource[0] = new Resource(ResourceType.COIN);
        availableResource[1] = new Resource(ResourceType.SERVANT);
        availableResource[2] = new Resource(ResourceType.SHIELD);
        availableResource[3] = new Resource(ResourceType.STONE);

        resourceBuffer.add(availableResource[choice]);

    }

    /**
     * Method setChosenResource assigns the resource parameter to the chosenResource variable
     */
    public void setChosenResource(int choice){
        this.chosenResource = choice;
    }

    /**
     * Method getChosenResource returns the choice of the wanted resource at the beginning of the game
     */
    public int getChosenResource() {
        return chosenResource;
    }

    /**
     * Method fillbuffer adds to the Player's resourceBuffer the resource from the deposit indicated by the parameter position,
     * decreasing the number of the resources of that specific deposit.
     * @param position (type Int) - it indicates which deposit we are considering
     */
    // TODO: 19/04/2021 da testare
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
