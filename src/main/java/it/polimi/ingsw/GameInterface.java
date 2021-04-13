package it.polimi.ingsw;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Deposit;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentSpace;
import it.polimi.ingsw.Cards.DevelopmentCards.ProductionPower;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the interface that contains the callable methods by Game
 */


public interface GameInterface {
    void setup() throws IOException;
    void buyDevCard(DevelopmentCard developmentCard, DevelopmentSpace developmentSpace);
    void moveResource(int position);
    void takeResourcesFromMarket(String line, int numline);
    void placeResource(int depositPosition, int resourcePosition);
    void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse);
    void useAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, ArrayList<ResourceType> newResources);
    void activateLeaderCard();
    void discardLeaderCard();
    void chooseLeaderCard();
}
