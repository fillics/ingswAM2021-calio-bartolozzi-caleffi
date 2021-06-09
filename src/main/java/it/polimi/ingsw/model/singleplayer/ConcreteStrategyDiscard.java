package it.polimi.ingsw.model.singleplayer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.polimi.ingsw.model.cards.developmentcards.CardColor;

import java.util.stream.IntStream;

/**
 * Represents the token that discards the Development Cards of the indicated type.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ConcreteStrategyDiscard implements TokenActionStrategy{

    private SinglePlayerGameInterface singleGame;
    private CardColor color;


    /**
     * Constructor ConcreteStrategyDiscard creates a new ConcreteStrategyDiscard instance.
     * @param singleGame of type SinglePlayerGame
     * @param color of type CardColor
     */
    public ConcreteStrategyDiscard(SinglePlayerGameInterface singleGame, CardColor color) {
        this.singleGame = singleGame;
        this.color = color;
    }

    /**
     * Override method effect calls the method removeCardFromDevelopmentDeck to discard a certain amount of cards from
     * the Development Deck, according to their color
     */
    @Override
    public void effect(){
        int amount = 2;
        IntStream.range(0, amount).forEach(i -> singleGame.removeDevCard(color));
    }



}
