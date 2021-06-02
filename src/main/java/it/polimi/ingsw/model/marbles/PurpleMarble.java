package it.polimi.ingsw.model.marbles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.Player;

/**
 * Represents a purple marble that can be taken from the market tray
 */

public class PurpleMarble extends Marble{
    private String path;

    @JsonCreator
    public PurpleMarble(@JsonProperty("path") String path) {
        this.path=path;
    }

    /**
     * Override method transform is used to transform a purple marble into a servant and to fill resourceBuffer of Player with it
     */
    @Override
    public void transform(Player player){
        Resource servant= new Resource(ResourceType.SERVANT);
        player.getResourceBuffer().add(servant);
    }

    public String getPath() {
        return path;
    }
}
