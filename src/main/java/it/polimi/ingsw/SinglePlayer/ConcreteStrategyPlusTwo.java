package it.polimi.ingsw.SinglePlayer;

import it.polimi.ingsw.Exceptions.NegativeNumberBlackCross;

/**
 * Represents the token with the effect of moving forward the Black Cross by 2 spaces.
 */
public class ConcreteStrategyPlusTwo implements TokenActionStrategy{

    private final int value = 2;
    private SinglePlayerGame single;

    /**
     * Constructor ConcreteStrategyPlusTwo creates a new ConcreteStrategyPlusTwo instance.
     * @param single of type SinglePlayerGame
     */
    public ConcreteStrategyPlusTwo(SinglePlayerGame single) {
        this.single = single;
    }

    /**
     * Override method effect calls the method increaseBlackCross to add to the black cross a certain value
     */
    @Override
    public void effect() throws NegativeNumberBlackCross {
        single.increaseBlackCross(value);
    }
}
