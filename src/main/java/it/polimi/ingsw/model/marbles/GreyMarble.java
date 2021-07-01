package it.polimi.ingsw.model.marbles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.Player;

/**
 * Represents a grey marble that can be taken from the market tray
 */

public class GreyMarble extends Marble{
    private final String path;

    @JsonCreator
    public GreyMarble(@JsonProperty("path") String path) {
        this.path=path;
    }

    /**
     * Override method transform is used to transform a grey marble into a stone and to fill resourceBuffer of Player with it
     */
    @Override
    public void transform(Player player){
        Resource stone= new Resource(ResourceType.STONE);
        player.getResourceBuffer().add(stone);
    }

    public String getPath() {
        return path;
    }
}
