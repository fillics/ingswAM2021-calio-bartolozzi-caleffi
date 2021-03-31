package it.polimi.ingsw.Board.FaithTrack;

import java.util.ArrayList;

public class VaticanReportSection {
    private ArrayList<Cell> section;
    private PopeFavorTile pope_favor_tile;

    /**
     * Class's Constructor made to define the attributes
     */
    public VaticanReportSection(ArrayList<Cell> section, PopeFavorTile pope_favor_tile) {
        this.section = section;
        this.pope_favor_tile = pope_favor_tile;
    }

    /**
     * Get-methods in order to obtain the attributes' values
     */
    public ArrayList<Cell> getSection() {
        return section;
    }

    public PopeFavorTile getPope_favor_tile() {
        return pope_favor_tile;
    }
}
