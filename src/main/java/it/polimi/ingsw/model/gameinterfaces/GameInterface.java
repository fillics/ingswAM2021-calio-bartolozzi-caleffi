package it.polimi.ingsw.model.gameinterfaces;

import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.*;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.marbles.Marble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Interface GameInterface represents the interface that contains the callable methods by Game.
 * For the communication model-server, this interface is passed to the methods that want to modify the model
 */
public interface GameInterface {
    void setState(GameStates gameStates);
    void createNewPlayer(String username, Integer idClient);
    void chooseDiscountActivation(ArrayList<Integer> idLeaderCards) throws DiscountCannotBeActivated;
    void buyDevCard(int idCard, ArrayList<ResourceType> chosenResources, ArrayList<Warehouse> chosenWarehouses, DevelopmentSpace developmentSpace) throws DevelopmentCardNotFound, DevCardNotPlaceable, NotEnoughResources, WrongChosenResources, DifferentDimension, EmptyDeposit, DepositDoesntHaveThisResource;
    void moveResource(int position) throws EmptyDeposit;
    void placeResource(int depositPosition, int resourcePosition) throws DepositHasReachedMaxLimit, DepositHasAnotherResource, AnotherDepositContainsThisResource, InvalidResource;
    void takeResourceFromMarket(String line, int numline ,ArrayList<Integer> whiteMarbleCardChoice) throws LeaderCardNotFound, LeaderCardNotActivated;
    void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, ArrayList<ResourceType> newResources) throws DifferentDimension, TooManyResourcesRequested, EmptyDeposit, DepositDoesntHaveThisResource, NotEnoughChosenResources, EmptyProductionPower;
    void activateLeaderCard(int idCardToActivate) throws LeaderCardNotFound, NotEnoughRequirements;
    void discardLeaderCard(int idCardToDiscard) throws LeaderCardNotFound, LeaderCardIsActive;
    void chooseLeaderCardToRemove(int idCard1, int idCard2, String username) throws LeaderCardNotFound;
    int getCurrentPlayer();
    ArrayList<DevelopmentCard> getDevGridLite();
    void additionalResourceSetup(ResourceType resourceType, int depositPosition, String username) throws DifferentDimension, DepositHasReachedMaxLimit, DepositHasAnotherResource, AnotherDepositContainsThisResource, InvalidResource;
    GameStates getState();
    ArrayList<Player> getActivePlayers();
    ArrayList<Player> getPlayers();
    HashMap<String, Player> getUsernameClientActivePlayers();
    boolean isEndgame();
    ArrayList<LinkedList<DevelopmentCard>> getDevelopmentGrid();
    Marble[][] getTable();
    Marble getRemainingMarble();
    int getIdGame();
    void nextPlayer();
    String getWinner();
    void disconnectPlayer(String username);
    void reconnectPlayer(String username);
    void setCurrentPlayer(int currentPlayer);
    int getIndexOfActivePlayer(String usernameToFind);

}
