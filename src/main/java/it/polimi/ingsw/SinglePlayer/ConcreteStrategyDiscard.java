package it.polimi.ingsw.SinglePlayer;

/**
 * Represents the token that discards the Development Cards of the indicated type.
 */

public class ConcreteStrategyDiscard implements TokenActionStrategy{

    @Override
    public boolean effect(){
        System.out.println("sono una risorsa da sa");
        return false;

    }
}
