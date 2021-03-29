package it.polimi.ingsw.SinglePlayer;

/**
 * Represents the token with the effect of moving the Black Cross forward by 2 spaces.
 */

public class ConcreteStrategyPlusTwo implements TokenActionStrategy{

    @Override
    public boolean effect(){
        System.out.println("prova");
        return true;
    }
}
