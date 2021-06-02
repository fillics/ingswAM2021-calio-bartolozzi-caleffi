package it.polimi.ingsw.model.board.resources;

/**
 * Type of the Resources that are present in the game
 */
public enum ResourceType {
    FAITHMARKER("/images/punchboard/croce.png"),
    COIN("/images/punchboard/coin.png"),
    STONE("/images/punchboard/stone.png"),
    SERVANT("/images/punchboard/servant.png"),
    SHIELD("/images/punchboard/shield.png"),
    JOLLY("");

    public final String  path;

    ResourceType(String path) {
        this.path = path;
    }
}
