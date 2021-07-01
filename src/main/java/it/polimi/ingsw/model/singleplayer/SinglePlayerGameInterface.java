package it.polimi.ingsw.model.singleplayer;

import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;

import java.util.ArrayList;

/**
 * Interface SinglePlayerGameInterface represents the interface that contains the callable SinglePlayerGame's methods by Player
 */
public interface SinglePlayerGameInterface {
    void increaseBlackCross(int amount);
    void shuffleSoloActionToken();
    void removeDevCard(CardColor color);
    int getBlackCross();
}
