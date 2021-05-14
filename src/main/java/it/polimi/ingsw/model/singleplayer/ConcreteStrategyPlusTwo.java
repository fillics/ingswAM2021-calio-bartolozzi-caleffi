package it.polimi.ingsw.model.singleplayer;

/**
 * Represents the token with the effect of moving forward the Black Cross by 2 spaces.
 */
public class ConcreteStrategyPlusTwo implements TokenActionStrategy{

    private final int steps = 2;
    private final SinglePlayerGameInterface single;

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
