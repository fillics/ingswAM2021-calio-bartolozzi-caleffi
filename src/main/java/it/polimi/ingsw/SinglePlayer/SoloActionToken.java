package it.polimi.ingsw.SinglePlayer;


/**
 * Represents the tokens that can be used in Single Mode.
 */

public class SoloActionToken {

    private SoloActionTokenType type;
    private TokenActionStrategy strategy;

    public SoloActionToken(TokenActionStrategy strategy){
        this.strategy = strategy;
    }

    public boolean applyEffect(){
        strategy.effect();
        return true;
    }


}
