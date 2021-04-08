package it.polimi.ingsw.SinglePlayer;

import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Game;

import java.util.stream.IntStream;

/**
 * Represents the token that discards the Development Cards of the indicated type.
 */

public class ConcreteStrategyDiscard implements TokenActionStrategy{

    private final int amount = 2;
    private Game game;
    private CardColor color;


    /**
     * Constructor ConcreteStrategyDiscard creates a new ConcreteStrategyDiscard instance.
     * @param game of type Game
     * @param color of type CardColor
     */
    public ConcreteStrategyDiscard(Game game, CardColor color) {
        this.game = game;
        this.color = color;
    }

    /**
     * Override method effect calls the method removeCardFromDevelopmentDeck to discard a certain amount of cards from
     * the Development Deck, according to their color
     */
    @Override
    public void effect(){
        IntStream.range(0, amount).forEach(i -> game.removeCardFromDevelopmentDeck(color));
    }

}
