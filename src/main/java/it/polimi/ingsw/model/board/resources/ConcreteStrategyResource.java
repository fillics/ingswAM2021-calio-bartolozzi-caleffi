package it.polimi.ingsw.model.board.resources;


import it.polimi.ingsw.exceptions.AnotherDepositContainsThisResource;
import it.polimi.ingsw.model.board.BoardInterface;
import it.polimi.ingsw.exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.exceptions.DepositHasReachedMaxLimit;
import it.polimi.ingsw.model.board.storage.Deposit;


/**
 * Class ConcreteStrategyResource represents the strategy linked to the COIN,STONE,SERVANT,SHIELD resources in order to
 * place them in a specific deposit
 */
public class ConcreteStrategyResource implements ResourceActionStrategy{
    private final int position;
    private final BoardInterface board;
    private final ResourceType resourcetype;

    /**
     * Class's Constructor made to define the concrete strategy
     * @param position is the deposit's position in the array
     * @param board is the board in which is inserted the resource
     * @param resourcetype is the type of resource inserted in the deposit
     */
    public ConcreteStrategyResource(int position, BoardInterface board, ResourceType resourcetype) {
        this.position = position;
        this.board = board;
        this.resourcetype = resourcetype;
    }

    /**
     * Override method linked to the useResource method used to operate on all resources except the faith marker
     * @throws DepositHasAnotherResource exception thrown when the deposit chosen to place the resource has already another type of resource in it
     * @throws DepositHasReachedMaxLimit exception thrown when the deposit is full
     */
    @Override
    public void action() throws DepositHasAnotherResource, DepositHasReachedMaxLimit, AnotherDepositContainsThisResource {
        for(int i = 0; i < board.getDeposits().size(); i++){
            if(i != position){
                if(board.getDeposits().get(i).getResourcetype() == resourcetype){
                    throw new AnotherDepositContainsThisResource();
                }
            }
        }
        if(board.getDeposits().get(position).getResourcetype() != resourcetype && board.getDeposits().get(position).getResourcetype() != null){
            throw new DepositHasAnotherResource();
        }
        else if(board.getDeposits().get(position).getQuantity() == board.getDeposits().get(position).getMaxLimit()){
            throw new DepositHasReachedMaxLimit();
        }
        else {
            if(board.getDeposits().get(position).getResourcetype() == null){
                board.getDeposits().get(position).setResourcetype(resourcetype);
            }
            board.getDeposits().get(position).increaseNumberOfResources();
        }
    }
}
