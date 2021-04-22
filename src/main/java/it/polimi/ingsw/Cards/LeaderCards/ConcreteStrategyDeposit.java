package it.polimi.ingsw.Cards.LeaderCards;
import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.BoardInterface;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Deposit;

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
