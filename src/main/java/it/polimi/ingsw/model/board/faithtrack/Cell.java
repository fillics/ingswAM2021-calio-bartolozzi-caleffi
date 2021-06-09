package it.polimi.ingsw.model.board.faithtrack;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class Cell represents the cells that make up the faith track
 */
public class Cell {
    private final int victoryPoint;
    private final boolean popeSpace;
    private final int vaticanReportSection;

    /**
     * Constructor Cell creates a new Cell instance.
     * @param victoryPoint (type Int) - it indicates how many victory points the cell contains
     * @param popeSpace (type boolean) - it indicates if the cell is a pope space
     * @param vaticanReportSection (type Int) - it indicates to which vatican report section it belongs
     */
    @JsonCreator
    public Cell(@JsonProperty("victoryPoint")int victoryPoint,@JsonProperty("popeSpace") boolean popeSpace,@JsonProperty("vaticanReportSection") int vaticanReportSection) {
        this.victoryPoint = victoryPoint;
        this.popeSpace = popeSpace;
        this.vaticanReportSection = vaticanReportSection;
    }

    /**
     * Method getVictoryPoint returns the number of victory points of the cell
     */
    public int getVictoryPoint() {
        return victoryPoint;
    }

    /**
     * Method isPopeSpace returns the boolean attribute to know if the cell is a pope space
     */
    public boolean isPopeSpace() {
        return popeSpace;
    }


    /**
     * Method getVaticanReportSection returns the vatican report section the cell belongs
     */
    public int getVaticanReportSection() {
        return vaticanReportSection;
    }
}
