package it.polimi.ingsw.model.board.faithtrack;

/**
 * Class PopeFavorTile represents the pope favor tiles that are present on the faith track
 */
public class PopeFavorTile {
    private PopeFavorTileColor color;
    private int victorypoint;
    private boolean visible;

    /**
     * Constructor PopeFavorTile creates a new PopeFavorTile instance.
     * @param color (type PopeFavorTileColor) - it indicates which color the tile is
     * @param victorypoint (type Int) - it indicates how many victory points the tile contains
     */
    public PopeFavorTile(PopeFavorTileColor color, int victorypoint) {
        this.color = color;
        this.victorypoint = victorypoint;
        visible = false;
    }

    /**
     * Method getVictoryPoint returns the number of victory points of the pope favor tile
     */
    public int getVictorypoint() {
        return victorypoint;
    }

    /**
     * Method getColor returns the color of the pope favor tile
     */
    public PopeFavorTileColor getColor() {
        return color;
    }

    /**
     * Method isVisible returns the boolean attribute to know if the pope favor tile is visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Method setVisible changes the value of the attribute visible, to make the favor tile visible
     */
    public void setVisible() { visible = true; }
}
