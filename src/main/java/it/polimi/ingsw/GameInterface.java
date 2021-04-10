package it.polimi.ingsw;

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
    void moveResource();
    void takeAndPlaceResource();
    void useAndChooseProdPower(ArrayList<ProductionPower> productionPowers);
    void activateLeaderCard();
    void discardLeaderCard();
    void chooseLeaderCard();
}
