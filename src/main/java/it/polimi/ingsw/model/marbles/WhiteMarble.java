package it.polimi.ingsw.model.marbles;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.Player;

/**
 * Represents a white marble that can be taken from the market tray
 */

public class WhiteMarble extends Marble{
    private final String path;

    @JsonCreator
    public WhiteMarble(@JsonProperty("path") String path) {
        this.path=path;
    }

    /**
     * Override method transform is used to do nothing when the marble is white,
     * unless a marble ability of leader cards is activated.
     */
    @Override
    public void transform(Player player) {
        int id0,i;
        if (!player.getWhiteMarbleCardChoice().isEmpty()) {
            id0= player.getWhiteMarbleCardChoice().get(0);
            for(i=0; i<player.getLeaderCards().size();i++) {
                if (player.getLeaderCards().get(i).getId() == id0) {
                    Resource newResource = new Resource(player.getLeaderCards().get(i).getStrategy().getResourceType());
                    player.getResourceBuffer().add(newResource);
                    player.getWhiteMarbleCardChoice().remove(0);
                    break;
                }
            }
        }
    }

    public String getPath() {
        return path;
    }
}

