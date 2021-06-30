package it.polimi.ingsw.model.cards.leadercards;
import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;
import it.polimi.ingsw.model.board.BoardInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Deposit;

/**
 * This class represents the deposit strategy for Leader Cards.
 */

public class ConcreteStrategyDeposit implements LeaderCardStrategy{
    private final ResourceType resourceType;
    private Deposit extraDeposit;
    private final BoardInterface board;
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

    public ConcreteStrategyDeposit() {
        this.resourceType = null;
        this.extraDeposit = null;
        this.board = null;
        this.active = false;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    @Override
    public void ability() {
        if(!active){
            extraDeposit = new Deposit(2, true);
            extraDeposit.setResourcetype(resourceType);
            assert board != null;
            board.getDeposits().add(extraDeposit);
            active = true;
        }
    }

    public String toString(){
        String escape= "";
        assert resourceType != null;
        if(resourceType.equals(ResourceType.COIN))
            escape= escape + Color.ANSI_YELLOW.escape() + Printable.SQUARE.print() + " " + Printable.SQUARE.print()+ Color.RESET;
        if(resourceType.equals(ResourceType.SERVANT))
            escape = escape + Color.ANSI_PURPLE.escape() + Printable.SQUARE.print()+ " "  + Printable.SQUARE.print() + Color.RESET;
        if(resourceType.equals(ResourceType.SHIELD))
            escape = escape + Color.ANSI_BLUE.escape() + Printable.SQUARE.print() + " " + Printable.SQUARE.print() + Color.RESET;
        if(resourceType.equals(ResourceType.STONE))
            escape = escape + Color.ANSI_GREY.escape() + Printable.SQUARE.print() + " " + Printable.SQUARE.print() + Color.RESET;
        escape= escape + "     ";
        return escape;
    }

}
