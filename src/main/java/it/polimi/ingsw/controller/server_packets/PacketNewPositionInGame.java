package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;


public class PacketNewPositionInGame  implements ServerPacketHandler {

    private final int posInGame;

     
    
    @JsonCreator
    public PacketNewPositionInGame(@JsonProperty("newPosInGame") int posInGame) {
        this.posInGame = posInGame;
    }

    public int getPosInGame() {
        return posInGame;
    }

    // TODO: 22/05/2021 togliere il system out con il messaggio
    @Override
    public void execute(Client client) {
        System.out.println("Someone disconnected/reconnected. Your new turn position is: "+(posInGame+1));
        client.getClientModelView().getMyPlayer().setPosInGame(posInGame);
    }
}
