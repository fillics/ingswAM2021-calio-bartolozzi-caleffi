package it.polimi.ingsw.model;

import it.polimi.ingsw.model.board.Board;

import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyDeposit;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyProductionPower;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.exceptions.EmptyDeposit;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.model.cards.leadercards.LeaderCardType;

import java.util.ArrayList;

/**
 * Player class represents the user and player of the board game.
 *
 */

public class Player {
    private final String username;
    private int idClient; // TODO: 02/05/2021 scrivere nel costruttore e metodo get
    private int totalVictoryPoint;
    private ArrayList<LeaderCard> leaderCards;
    private ArrayList<Resource> resourceBuffer;
    private Board board;
    private GamePlayerInterface gamePlayer;
    private int chosenResource;
    private ArrayList<Integer> whiteMarbleCardChoice;

    /**
     * Constructor Player creates a new Player instance.
     *
     * @param username of type String.
     */
    public Player(String username, Game game) {
        this.username = username;
        totalVictoryPoint = 0;
        leaderCards = new ArrayList<>();
        resourceBuffer = new ArrayList<>();
        whiteMarbleCardChoice = new ArrayList<>();

        board = new Board(game);
        this.gamePlayer = game;
    }


    public ArrayList<Integer> getWhiteMarbleCardChoice() {
        return whiteMarbleCardChoice;
    }


    public void setWhiteMarbleCardChoice(ArrayList<Integer> whiteMarbleCardChoice) {
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
     * Method getTotalVictoryPoint returns the amount of Player's victory points.
     */
    public int getTotalVictoryPoints() {
        totalVictoryPoint = 0;
        totalVictoryPoint += board.getBoardVictoryPoint();
        for(LeaderCard leaderCard : leaderCards){
            if(leaderCard.getStrategy().isActive()){
                totalVictoryPoint += leaderCard.getVictoryPoint();
            }
        }
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

        if (card.getType().equals(LeaderCardType.PRODUCTION_POWER)) card.setStrategy(new ConcreteStrategyProductionPower(getBoard(),card.getResourceType() ));
        if (card.getType().equals(LeaderCardType.EXTRA_DEPOSIT)) card.setStrategy(new ConcreteStrategyDeposit(card.getResourceType(), getBoard()));

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
    public void fillBuffer (int position) throws EmptyDeposit {
        resourceBuffer.add(board.getDeposits().get(position).takeResource());
    }

    /**
     * Method endTurn called when a player wants to end his own turn
     */
    public void endTurn(){
        if(resourceBuffer.size() > 0){
            gamePlayer.increaseFaithMarkerOfOtherPlayers();
        }
        resourceBuffer.clear();
    }
}
