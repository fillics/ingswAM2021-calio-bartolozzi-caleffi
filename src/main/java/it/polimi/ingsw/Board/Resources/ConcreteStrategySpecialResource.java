package it.polimi.ingsw.Board.Resources;

import it.polimi.ingsw.Board.BoardInterface;

// TODO: 23/04/2021 JAVADOC - dire che riguarda le risorse che incrementano il faith marker
public class ConcreteStrategySpecialResource implements ResourceActionStrategy {
    BoardInterface board;

    /**
     * Constructor ConcreteStrategySpecialResource creates a new ConcreteStrategySpecialResource instance.
     * @param board (type BoardInterface) - to use the board's method increaseFaithMarker
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
