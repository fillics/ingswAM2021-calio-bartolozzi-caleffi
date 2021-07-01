package it.polimi.ingsw.model.marbles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.Player;

/**
 * Represents a red marble that can be taken from the market tray
 */

public class RedMarble extends Marble{
    private final String path;

    @JsonCreator
    public RedMarble(@JsonProperty("path") String path) {
        this.path=path;
    }
    /**
     * Override method transform is used to transform a red marble into a faith marker and to fill resourceBuffer of Player with it
     */
    @Override
    public void transform(Player player){
        player.getBoard().increaseFaithMarker();
    }

    public String getPath() {
        return path;
    }
}
