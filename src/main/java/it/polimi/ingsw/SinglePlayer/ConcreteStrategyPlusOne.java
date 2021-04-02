package it.polimi.ingsw.SinglePlayer;

/**
 * Represents the token with the effect of moving the Black Cross forward by 1 space and shuffling the tokens.
 */

public class ConcreteStrategyPlusOne implements TokenActionStrategy{

    private final int amount = 1;
    private SinglePlayerGame single;

    public ConcreteStrategyPlusOne(SinglePlayerGame single) {
        this.single = single;
    }

    @Override
    public boolean effect(){

        single.increaseBlackCross(amount);
        single.shuffleSoloActionToken();

        return true;
    }
}
