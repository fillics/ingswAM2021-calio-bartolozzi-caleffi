package it.polimi.ingsw;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Cards.DevelopmentCards.*;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Exceptions.*;

import java.util.ArrayList;

/**
 * Represents the interface that contains the callable methods by Game
 */


public interface GameInterface {
    void setup();
    void chooseDiscountActivation(LeaderCard leaderCard, boolean choice)throws DiscountCannotBeActivated;
    void buyDevCard(CardColor color, Level level, ArrayList<ResourceType> chosenResources, ArrayList<Warehouse> chosenWarehouses, DevelopmentSpace developmentSpace) throws DevelopmentCardNotFound, DevCardNotPlaceable, NotEnoughResources, WrongChosenResources, DifferentDimension;
    void moveResource(int position);
    void placeResource(int depositPosition, int resourcePosition);
    void takeResourcesFromMarket(String line, int numline);
    void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse) throws DifferentDimension, TooManyResourcesRequested;
    void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, ArrayList<ResourceType> newResources) throws DifferentDimension, TooManyResourcesRequested;
    void activateLeaderCard(LeaderCard cardToActivate) throws LeaderCardNotFound;
    void discardLeaderCard(LeaderCard cardToDiscard) throws LeaderCardNotFound;
    void chooseLeaderCard(LeaderCard chosenCard1, LeaderCard chosenCard2) throws LeaderCardNotFound;
}
