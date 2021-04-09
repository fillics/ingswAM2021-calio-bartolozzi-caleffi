package it.polimi.ingsw;

import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentSpace;

import java.io.IOException;

/**
 * Represents the interface that contains the callable methods by Game
 */


public interface GameInterface {

    void setup() throws IOException;
    void buyDevCard(DevelopmentCard developmentCard, DevelopmentSpace developmentSpace);
    void moveResource();
    void takeAndPlaceResource();
    void useAndChooseProdPower();
    void activateLeaderCard();
    void discardLeaderCard();
    void chooseLeaderCard();
}
