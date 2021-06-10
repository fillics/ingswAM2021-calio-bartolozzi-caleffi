package it.polimi.ingsw.model.singleplayer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the token with the effect of moving forward the Black Cross by 2 spaces.
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ConcreteStrategyPlusTwo implements TokenActionStrategy{

    private int steps = 2;
    private SinglePlayerGameInterface single;

    /**
     * Constructor ConcreteStrategyPlusTwo creates a new ConcreteStrategyPlusTwo instance.
     * @param single of type SinglePlayerGame
     */
    public ConcreteStrategyPlusTwo(SinglePlayerGameInterface single) {
        this.single = single;
    }

    /**
     * Override method effect calls the method increaseBlackCross to add to the black cross a certain value
     */
    @Override
    public void effect(){
        single.increaseBlackCross(steps);
    }


}
