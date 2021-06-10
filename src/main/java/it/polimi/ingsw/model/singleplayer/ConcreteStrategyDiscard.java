package it.polimi.ingsw.model.singleplayer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;

import java.util.stream.IntStream;

/**
 * Represents the token that discards the Development Cards of the indicated type.
 */

public class ConcreteStrategyDiscard implements TokenActionStrategy{

    private final int amount = 2;
    private SinglePlayerGameInterface singleGame;
    private CardColor color;


    /**
     * Constructor ConcreteStrategyDiscard creates a new ConcreteStrategyDiscard instance.
     * @param singleGame of type SinglePlayerGame
     * @param color of type CardColor
     */
    @JsonCreator
    public ConcreteStrategyDiscard(SinglePlayerGameInterface singleGame,CardColor color) {
        this.singleGame = singleGame;
        this.color = color;
    }

    public SinglePlayerGameInterface getSingleGame() {
        return singleGame;
    }

    public CardColor getColor() {
        return color;
    }

    /**
     * Override method effect calls the method removeCardFromDevelopmentDeck to discard a certain amount of cards from
     * the Development Deck, according to their color
     */
    @Override
    public void effect(){
        IntStream.range(0, amount).forEach(i -> singleGame.removeDevCard(color));
    }

}
