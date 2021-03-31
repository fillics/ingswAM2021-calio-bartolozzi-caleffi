package it.polimi.ingsw.Board.Resources;


import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Storage.Deposit;

public class ConcreteStrategyResource implements ResourceActionStrategy{
    private int position;
    private Board board;
    private ResourceType resourcetype;

    /**
     * Class's Constructor made to define the concrete strategy
     * @param position is the deposit's position in the array
     * @param board is the board in which is inserted the resource
     * @param resourcetype is the type of resource inserted in the deposit
     */
    public ConcreteStrategyResource(int position, Board board, ResourceType resourcetype) {
        this.position = position;
        this.board = board;
        this.resourcetype = resourcetype;
    }

    /**
     * Override method linked to the useResource method used to operate on all resources except the faith marker
     */
    @Override
    public boolean action() {
        board.getDeposits().get(position).increaseNumberOfResources(1);
        board.getDeposits().get(position).setResourcetype(resourcetype);
        return true;
    }
}
