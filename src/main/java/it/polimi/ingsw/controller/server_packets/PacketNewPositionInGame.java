package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;

/**
 * PacketNewPositionInGame sets the new turn's position of the player in the Game after a disconnection/reconnection
 */
public class PacketNewPositionInGame implements ServerPacketHandler {

    private final int posInGame;
    private final String username;
    private final String action;

    /**
     * Class' constructor.
     * @param posInGame is the value of the new position in the game.
     * @param username is the username of the player.
     * @param action is the action for which the player got a new position.
     */
    @JsonCreator
    public PacketNewPositionInGame(@JsonProperty("newPosInGame") int posInGame,
                                   @JsonProperty("username") String username,
                                   @JsonProperty("action") String action) {
        this.posInGame = posInGame;
        this.username=username;
        this.action=action;
    }

    /**
     * Method execute() sends a message to the client and updates the posInGame value in LitePlayer class.
     */
    @Override
    public void execute(Client client) {
        switch (client.getViewChoice()){
            case CLI -> System.out.println(username + " "+ action + ". "+ "Your new turn position is: "+(posInGame+1));
            case GUI -> client.getGui().createMessageFromServer(username + " "+ action + ". "+ "Your new turn position is: "+(posInGame+1));
        }

        client.getClientModelView().getMyPlayer().setPosInGame(posInGame);
    }

    public int getPosInGame() {
        return posInGame;
    }

    public String getUsername() {
        return username;
    }

    public String getAction() {
        return action;
    }
}
