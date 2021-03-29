package it.polimi.ingsw.Board.FaithTrack;

public class PopeFavorTile {
    private PopeFavorTileColor color;
    private int victorypoint;
    private boolean visible;

    public PopeFavorTile(PopeFavorTileColor color, int victorypoint, boolean visible) {
        this.color = color;
        this.victorypoint = victorypoint;
        this.visible = visible;
    }

    public int getVictorypoint() {
        return victorypoint;
    }

    public PopeFavorTileColor getColor() {
        return color;
    }

    public boolean isVisible() {
        return visible;
    }
}
