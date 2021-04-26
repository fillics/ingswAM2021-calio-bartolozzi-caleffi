package it.polimi.ingsw.model.cards.leadercards;
import it.polimi.ingsw.model.board.BoardInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Deposit;

/**
 * This class represents the deposit strategy for Leader Cards.
 */

public class ConcreteStrategyDeposit implements LeaderCardStrategy{
    private ResourceType resourceType;
    private Deposit extraDeposit;
    private BoardInterface board;
    private boolean active;

    public boolean isActive() {
        return active;
    }

    /**
     * Constructor ConcreteStrategyDeposit creates a new ConcreteStrategyDeposit instance.
     */
    public ConcreteStrategyDeposit(ResourceType resourceType,BoardInterface board) {
        this.resourceType = resourceType;
        this.board=board;
        active = false;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public void ability() {
        if(!active){
            extraDeposit = new Deposit(2);
            extraDeposit.setResourcetype(resourceType);
            board.getDeposits().add(extraDeposit);
            active = true;
        }
    }

}
