package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.*;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.cards.leadercards.ResourcesRequirement;
import it.polimi.ingsw.model.marbles.Marble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Interface GameInterface represents the interface that contains the callable methods by Game
 */

public interface GameInterface {
    void setState(State state);
    void createNewPlayer(String username, Integer idClient);
    void chooseDiscountActivation(ArrayList<Integer> idLeaderCards) throws DiscountCannotBeActivated;
    void buyDevCard(int idCard, ArrayList<ResourceType> chosenResources, ArrayList<Warehouse> chosenWarehouses, DevelopmentSpace developmentSpace) throws DevelopmentCardNotFound, DevCardNotPlaceable, NotEnoughResources, WrongChosenResources, DifferentDimension, EmptyDeposit, DepositDoesntHaveThisResource;
    void moveResource(int position) throws EmptyDeposit;
    void placeResource(int depositPosition, int resourcePosition) throws DepositHasReachedMaxLimit, DepositHasAnotherResource;
    void takeResourceFromMarket(String line, int numline ,ArrayList<Integer> whiteMarbleCardChoice) throws LeaderCardNotFound, LeaderCardNotActivated;
    void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, ArrayList<ResourceType> newResources) throws DifferentDimension, TooManyResourcesRequested, EmptyDeposit, DepositDoesntHaveThisResource;
    void activateLeaderCard(int idCardToActivate) throws LeaderCardNotFound, NotEnoughRequirements;
    void discardLeaderCard(int idCardToDiscard) throws LeaderCardNotFound;
    void chooseLeaderCardToRemove(int idCard1, int idCard2) throws LeaderCardNotFound;
    int getCurrentPlayer();
    int getNumof_players();
    void additionalResourceSetup(ResourceType resourceType, int depositPosition, int idClient) throws DifferentDimension, DepositHasReachedMaxLimit, DepositHasAnotherResource;
    State getState();
    ArrayList<Player> getActivePlayers();
    void setNumof_players(int numof_players);
    HashMap<Integer, Player> getIdClientActivePlayers();
    boolean isEndgame();
    ArrayList<LinkedList<DevelopmentCard>> getDevelopmentGrid();
    ArrayList<DevelopmentCard> getInitialDevGrid();
    Marble[][] getTable();
    int getIdGame();
}
