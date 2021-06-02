package it.polimi.ingsw.model.marbles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.Player;

/**
 * Represents a yellow marble that can be taken from the market tray
 */

public class YellowMarble extends Marble{
    private String path;

    @JsonCreator
    public YellowMarble(@JsonProperty("path") String path) {
        this.path=path;
    }

    /**
     * Override method transform is used to transform a yellow marble into a coin and to fill resourceBuffer of Player with it
     */
    @Override
    public void transform(Player player){
        Resource coin= new Resource(ResourceType.COIN);
        player.getResourceBuffer().add(coin);
    }

    public String getPath() {
        return path;
    }
}
