package it.polimi.ingsw.model;

import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.developmentcards.Level;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.exceptions.*;

import java.util.ArrayList;

/**
 * Interface GameInterface represents the interface that contains the callable methods by Game
 */

public interface GameInterface {

    void chooseDiscountActivation(ArrayList<LeaderCard> leaderCards) throws DiscountCannotBeActivated;
    void buyDevCard(CardColor color, Level level, ArrayList<ResourceType> chosenResources, ArrayList<Warehouse> chosenWarehouses, DevelopmentSpace developmentSpace) throws DevelopmentCardNotFound, DevCardNotPlaceable, NotEnoughResources, WrongChosenResources, DifferentDimension, EmptyDeposit, DepositDoesntHaveThisResource;
    void moveResource(int position) throws EmptyDeposit;
    void placeResource(int depositPosition, int resourcePosition) throws DepositHasReachedMaxLimit, DepositHasAnotherResource;
    void takeResourceFromMarket(String line, int numline ,ArrayList<LeaderCard> whiteMarbleCardChoice) throws LeaderCardNotFound, LeaderCardNotActivated;
    void takeResourcesFromMarket(String line, int numline);
    void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse) throws DifferentDimension, TooManyResourcesRequested, EmptyDeposit, DepositDoesntHaveThisResource;
    void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, ArrayList<ResourceType> newResources) throws DifferentDimension, TooManyResourcesRequested, EmptyDeposit, DepositDoesntHaveThisResource;
    void activateLeaderCard(LeaderCard cardToActivate) throws LeaderCardNotFound, NotEnoughRequirements;
    void discardLeaderCard(LeaderCard cardToDiscard) throws LeaderCardNotFound;
    void chooseLeaderCardToRemove(LeaderCard chosenCard1, LeaderCard chosenCard2) throws LeaderCardNotFound;
}
