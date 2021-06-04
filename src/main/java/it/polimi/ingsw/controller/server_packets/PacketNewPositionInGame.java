package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;


public class PacketNewPositionInGame  implements ServerPacketHandler {

    private final int posInGame;
    private final String username;
    private final String action;

     
    
    @JsonCreator
    public PacketNewPositionInGame(@JsonProperty("newPosInGame") int posInGame,
                                   @JsonProperty("username") String username,
                                   @JsonProperty("action") String action) {
        this.posInGame = posInGame;
        this.username=username;
        this.action=action;
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

    @Override
    public void execute(Client client) {
        if(client.getViewChoice().equals(ViewChoice.CLI)){
            System.out.println(username + " "+ action + ". "+ "Your new turn position is: "+(posInGame+1));
        }
        else client.getGui().createMessageFromServer(username + " "+ action + ". "+ "Your new turn position is: "+(posInGame+1));
        client.getClientModelView().getMyPlayer().setPosInGame(posInGame);
    }
}
