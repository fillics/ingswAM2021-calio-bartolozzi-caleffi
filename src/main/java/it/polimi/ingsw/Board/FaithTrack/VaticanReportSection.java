package it.polimi.ingsw.Board.FaithTrack;

import java.util.ArrayList;

public class VaticanReportSection {
    private ArrayList<Cell> section;
    private PopeFavorTile popefavortile;

    /**
     * Class's Constructor made to define the attributes
     */
    public VaticanReportSection(PopeFavorTile popefavortile) {
        section = new ArrayList<>();
        this.popefavortile = popefavortile;
    }
    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<Cell> getSection() {
        return section;
    }

    public PopeFavorTile getPopefavortile() {
        return popefavortile;
    }
}
