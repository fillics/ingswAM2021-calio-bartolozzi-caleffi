package it.polimi.ingsw.SinglePlayer;

import it.polimi.ingsw.Exceptions.NegativeNumberBlackCross;

/**
 * Represents the token with the effect of moving forward the Black Cross by 1 space and shuffling the tokens.
 */

public class ConcreteStrategyPlusOne implements TokenActionStrategy{

    private final int value = 1;
    private SinglePlayerGame single;

    /**
     * Constructor ConcreteStrategyPlusOne creates a new ConcreteStrategyPlusOne instance.
     * @param single of type SinglePlayerGame
     */
    public ConcreteStrategyPlusOne(SinglePlayerGame single) {
        this.single = single;
    }

    /**
     * Override method effect calls the method increaseBlackCross to add to the black cross a certain value and
     * then the method shuffleSoloActionToken.
     */
    @Override
    public void effect() throws NegativeNumberBlackCross {
        single.increaseBlackCross(value);
        single.shuffleSoloActionToken();

    }
}
