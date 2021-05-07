package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.EmptyDeposit;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.client.SocketClientConnected;
import it.polimi.ingsw.server.Server;

import java.net.Socket;

public class PacketMoveResource implements PacketHandler {
    private int position;

    @JsonCreator
    public PacketMoveResource(@JsonProperty("position: ")int position) {
        this.position = position;
    }


    @Override
    public void execute(Server server, GameInterface gameInterface, Socket socket) throws EmptyDeposit {
            gameInterface.moveResource(position);
    }
}
