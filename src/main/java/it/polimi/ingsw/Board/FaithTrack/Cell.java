package it.polimi.ingsw.Board.FaithTrack;

public class Cell {
    private int victoryPoint;
    private boolean popeSpace;

    /**
     * Class's Constructor made to define the attributes
     */
    public Cell(int victoryPoint, boolean popeSpace) {
        this.victoryPoint = victoryPoint;
        this.popeSpace = popeSpace;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     *
     */
    public int getVictoryPoint() {
        return victoryPoint;
    }

    public boolean isPopeSpace() {
        return popeSpace;
    }
}
