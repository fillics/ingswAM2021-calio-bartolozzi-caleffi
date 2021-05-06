package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.client.SocketClientConnected;

import java.net.Socket;

public class PacketChooseLeaderCardToRemove implements PacketHandler {
    private int ID1;
    private int ID2;

    @JsonCreator
    public PacketChooseLeaderCardToRemove(@JsonProperty("First card: ")int ID1,@JsonProperty("Second card: ") int ID2) {
        this.ID1 = ID1;
        this.ID2 = ID2;
    }


    @Override
    public void execute(GameInterface gameInterface, Socket socketClientConnected) throws LeaderCardNotFound {
        gameInterface.chooseLeaderCardToRemove(ID1,ID2);
    }
}
