package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.developmentcards.Level;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.cards.leadercards.ResourcesRequirement;

import java.util.ArrayList;

/**
 * Interface GameInterface represents the interface that contains the callable methods by Game
 */

public interface GameInterface {
    void setState(State state);
    void createNewPlayer(String username);
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
    void additionalResourceSetup(Resource resource, int depositPosition) throws DifferentDimension, DepositHasReachedMaxLimit, DepositHasAnotherResource;
    State getState();
    ArrayList<Player> getActivePlayers();
    void setNumof_players(int numof_players);
}
