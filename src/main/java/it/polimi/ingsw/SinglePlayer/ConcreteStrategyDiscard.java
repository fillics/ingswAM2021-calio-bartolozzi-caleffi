package it.polimi.ingsw.SinglePlayer;

import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Game;

/**
 * Represents the token that discards the Development Cards of the indicated type.
 */

public class ConcreteStrategyDiscard implements TokenActionStrategy{

    private Game game;
    private CardColor color;

    public ConcreteStrategyDiscard(Game game, CardColor color) {
        this.game = game;
        this.color = color;
    }

    @Override
    public boolean effect(){
        //game.removeCardFromDevelopmentDeck();
        return false;

    }

}
