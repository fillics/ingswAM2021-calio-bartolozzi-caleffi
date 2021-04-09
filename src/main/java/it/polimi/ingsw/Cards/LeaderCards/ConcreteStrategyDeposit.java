package it.polimi.ingsw.Cards.LeaderCards;
import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Deposit;

/**
 * This class represents the deposit strategy for Leader Cards.
 */

public class ConcreteStrategyDeposit implements LeaderCardStrategy{
    private ResourceType resourceType;
    private Deposit extraDeposit;
    private Board board;

    /**
     * Constructor ConcreteStrategyDeposit creates a new ConcreteStrategyDeposit instance.
     */
    public ConcreteStrategyDeposit(ResourceType resourceType,Board board) {
        this.resourceType = resourceType;
        this.board=board;
    }

    @Override
    public void ability() {
        extraDeposit = new Deposit(2);
        extraDeposit.setResourcetype(resourceType);
        board.getDeposits().add(extraDeposit);
    }

}
