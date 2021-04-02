package it.polimi.ingsw.SinglePlayer;

/**
 * Represents the token with the effect of moving the Black Cross forward by 2 spaces.
 */

public class ConcreteStrategyPlusTwo implements TokenActionStrategy{

    private final int amount = 2;
    private SinglePlayerGame single;

    public ConcreteStrategyPlusTwo(SinglePlayerGame single) {
        this.single = single;
    }

    @Override
    public boolean effect(){
        single.increaseBlackCross(2);
        return true;
    }
}
