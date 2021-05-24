package it.polimi.ingsw.model.board.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.board.BoardInterface;

/**
 * Class ConcreteStrategySpecialResource represents the strategy linked to the FAITHMARKER resource in order to
 * increase the position of the player faithmarker.
 */
public class ConcreteStrategySpecialResource implements ResourceActionStrategy {
    BoardInterface board;

    /**
     * Constructor ConcreteStrategySpecialResource creates a new ConcreteStrategySpecialResource instance.
     * @param board (type BoardInterface) - to use the board's method increaseFaithMarker
     */
    @JsonCreator
    public ConcreteStrategySpecialResource(@JsonProperty("board")BoardInterface board) {
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
