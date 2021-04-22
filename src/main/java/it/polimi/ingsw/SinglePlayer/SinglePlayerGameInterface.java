package it.polimi.ingsw.SinglePlayer;

import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;

public interface SinglePlayerGameInterface {
    void increaseBlackCross(int amount);
    void shuffleSoloActionToken();
    void removeDevCard(CardColor color);

}
