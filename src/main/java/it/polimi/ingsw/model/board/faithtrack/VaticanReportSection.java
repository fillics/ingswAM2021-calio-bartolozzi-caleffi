package it.polimi.ingsw.model.board.faithtrack;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Class VaticanReportSection represents the vatican report section that contains some cells
 */
public class VaticanReportSection {
    private final ArrayList<Cell> section;
    private final PopeFavorTile popefavortile;
    private boolean activated;

    /**
     * Constructor VaticanReportSection creates a new VaticanReportSection instance
     * @param popefavortile (type PopeFavorTile) - it indicates the pope favor tile present in the vatican section
     */
    @JsonCreator
    public VaticanReportSection(@JsonProperty("popefavortile")PopeFavorTile popefavortile) {
        section = new ArrayList<>();
        this.popefavortile = popefavortile;
        activated = false;
    }

    /**
     * Method getSection returns the array list of cells that belong to the vatican report section
     */
    public ArrayList<Cell> getSection() {
        return section;
    }
    /**
     * Method getPopefavortile returns the pope favor tile of the specific vatican report section
     */
    public PopeFavorTile getPopefavortile() {
        return popefavortile;
    }

    /**
     * Method setActivated changes the value of the attribute activated, to active the vatican report section
     */
    public void setActivated() {
        activated = true;
    }

    /**
     * Method isActivated returns the boolean attribute to know if the vatican report section is activated
     */
    public boolean isActivated() {
        return activated;
    }
}
