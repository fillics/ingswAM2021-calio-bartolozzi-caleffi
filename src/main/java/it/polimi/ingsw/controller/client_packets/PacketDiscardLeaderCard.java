package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.client.SocketClientConnected;
import it.polimi.ingsw.server.Server;

import java.net.Socket;

public class PacketDiscardLeaderCard implements PacketHandler {
    private int ID;

    @JsonCreator
    public PacketDiscardLeaderCard(@JsonProperty("ID: ")int ID) {
        this.ID = ID;
    }


    @Override
    public void execute(Server server, GameInterface gameInterface, Socket socket) throws LeaderCardNotFound {
        gameInterface.discardLeaderCard(ID);
    }
}
