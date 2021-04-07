package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Player;

public class ConcreteStrategySpecialResource implements ResourceActionStrategy {
    Board board;
    /**
     *Class's Constructor made to define the concrete strategy
     */
    public ConcreteStrategySpecialResource(Board board) {
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
