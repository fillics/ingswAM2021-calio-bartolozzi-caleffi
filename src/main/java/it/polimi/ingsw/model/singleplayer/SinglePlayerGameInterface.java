package it.polimi.ingsw.model.singleplayer;

import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;

import java.util.ArrayList;

public interface SinglePlayerGameInterface {
    void increaseBlackCross(int amount);
    void shuffleSoloActionToken();
    void removeDevCard(CardColor color);
    int getBlackCross();
}
