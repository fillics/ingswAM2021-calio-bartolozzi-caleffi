package it.polimi.ingsw.model.marbles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.Player;

/**
 * Represents a blue marble that can be taken from the market tray
 */

public class BlueMarble extends Marble{
    private final String path;

    @JsonCreator
    public BlueMarble(@JsonProperty("path") String path) {
        this.path=path;
    }
    /**
     * Override method transform is used to transform a blue marble into a shield and to fill resourceBuffer of Player with it
     */
    @Override
    public void transform(Player player){
        Resource shield= new Resource(ResourceType.SHIELD);
        player.getResourceBuffer().add(shield);
    }

    public String getPath() {
        return path;
    }
}
