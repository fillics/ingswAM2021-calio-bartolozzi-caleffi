package it.polimi.ingsw.SinglePlayer;

/**
 * Represents the token with the effect of moving the Black Cross forward by 1 space and shuffling the tokens.
 */

public class ConcreteStrategyPlusOne implements TokenActionStrategy{

    @Override
    public boolean effect(){
        System.out.println("sono una risorsa da s");
        return true;
    }
}
