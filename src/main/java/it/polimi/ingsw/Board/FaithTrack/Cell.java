package it.polimi.ingsw.Board.FaithTrack;


public class Cell {
    private int victoryPoint;
    private boolean popeSpace;
    private int vaticanReportSection;

    /**
     * Class's Constructor made to define the attributes
     */
    public Cell(int victoryPoint, boolean popeSpace, int vaticanReportSection) {
        this.victoryPoint = victoryPoint;
        this.popeSpace = popeSpace;
        this.vaticanReportSection = vaticanReportSection;
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

    public int getVaticaReportSection() {
        return vaticanReportSection;
    }
}
