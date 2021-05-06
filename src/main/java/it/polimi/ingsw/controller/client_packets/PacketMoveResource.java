package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.EmptyDeposit;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.client.SocketClientConnected;

public class PacketMoveResource implements PacketHandler {
    private int position;

    @JsonCreator
    public PacketMoveResource(@JsonProperty("position: ")int position) {
        this.position = position;
    }


    @Override
    public void execute(GameInterface gameInterface, SocketClientConnected socketClientConnected) throws EmptyDeposit {
            gameInterface.moveResource(position);
    }
}
