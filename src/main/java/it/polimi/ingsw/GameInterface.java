package it.polimi.ingsw;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentSpace;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;
import it.polimi.ingsw.Cards.LeaderCards.LeaderCard;
import it.polimi.ingsw.Exceptions.DifferentDimensionForProdPower;
import it.polimi.ingsw.Exceptions.LeaderCardNotFound;
import it.polimi.ingsw.Exceptions.TooManyResourcesRequested;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the interface that contains the callable methods by Game
 */


public interface GameInterface {
    void setup();
    void buyDevCard(DevelopmentCard developmentCard);
    void moveResource(int position);
    void placeResource(int depositPosition, int resourcePosition);
    void takeResourcesFromMarket(String line, int numline);
    void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse) throws DifferentDimensionForProdPower, TooManyResourcesRequested;
    void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, ArrayList<ResourceType> newResources) throws DifferentDimensionForProdPower, TooManyResourcesRequested;
    void activateLeaderCard(LeaderCard cardToActivate) throws LeaderCardNotFound;
    void discardLeaderCard(LeaderCard cardToDiscard) throws LeaderCardNotFound;
    void chooseLeaderCard(LeaderCard chosenCard1, LeaderCard chosenCard2) throws LeaderCardNotFound;
}
