package it.polimi.ingsw.model.singleplayer;

import it.polimi.ingsw.model.cards.developmentcards.CardColor;

public interface SinglePlayerGameInterface {
    void increaseBlackCross(int amount);
    void shuffleSoloActionToken();
    void removeDevCard(CardColor color);

}
