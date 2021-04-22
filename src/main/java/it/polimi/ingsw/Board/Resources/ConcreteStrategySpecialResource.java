package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Board.BoardInterface;

public class ConcreteStrategySpecialResource implements ResourceActionStrategy {
    BoardInterface board;
    /**
     *Class's Constructor made to define the concrete strategy
     */
    public ConcreteStrategySpecialResource(BoardInterface board) {
        this.board = board;
    }

    /**
     * Override method linked to the useResource method used to operate on the faith marker resource
     */
    @Override
    public void action() {
        board.increaseFaithMarker();
    }
}
