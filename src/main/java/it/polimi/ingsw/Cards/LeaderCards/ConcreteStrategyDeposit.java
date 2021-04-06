package it.polimi.ingsw.Cards.LeaderCards;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Deposit;

/**
 * This class represents the deposit strategy for Leader Cards.
 */

public class ConcreteStrategyDeposit extends LeaderCardStrategy{
    private ResourceType resourceType;
    private Deposit extraDeposit;

    /**
     * Constructor ConcreteStrategyDeposit creates a new ConcreteStrategyDeposit instance.
     */
    public ConcreteStrategyDeposit(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public boolean activate() {
        extraDeposit= new Deposit(2);
        extraDeposit.setResourcetype(resourceType);
        System.out.println("I'm a deposit leader card");
        return true;
    }

    public Deposit getExtraDeposit() {
        return extraDeposit;
    }
}
