package it.polimi.ingsw;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Represents the interface that contains the callable methods by Game
 */


public interface GameInterface {

    void setup() throws IOException;
    void buyDevCard();
    void moveResource();
    void takeAndPlaceResource();
    void useAndChooseProdPower();
    void activateLeaderCard();
    void discardLeaderCard();
    void chooseLeaderCard();
}
