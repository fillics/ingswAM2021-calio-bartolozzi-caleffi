package it.polimi.ingsw.model.singleplayer;

/**
 * Represents the token with the effect of moving forward the Black Cross by 1 space and shuffling the tokens.
 */
public class ConcreteStrategyPlusOne implements TokenActionStrategy{

    private final int steps = 1;
    private SinglePlayerGameInterface single;

    /**
     * Constructor ConcreteStrategyPlusOne creates a new ConcreteStrategyPlusOne instance.
     * @param single of type SinglePlayerGame
     */
    public ConcreteStrategyPlusOne(SinglePlayerGameInterface single) {
        this.single = single;
    }

    /**
     * Override method effect calls the method increaseBlackCross to add to the black cross a certain value and
     * then the method shuffleSoloActionToken.
     */
    @Override
    public void effect(){
        single.increaseBlackCross(steps);
        single.shuffleSoloActionToken();
    }
}
