package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.DepositHasAnotherResource;
import it.polimi.ingsw.exceptions.DepositHasReachedMaxLimit;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.client.SocketClientConnected;
import it.polimi.ingsw.server.Server;

import java.net.Socket;

public class PacketPlaceResource implements PacketHandler {
    private int depositPosition;
    private int resourcePosition;

    @JsonCreator
    public PacketPlaceResource(@JsonProperty("deposit position: ")int depositPosition,@JsonProperty("resource position: ") int resourcePosition) {
        this.depositPosition = depositPosition;
        this.resourcePosition = resourcePosition;
    }



    @Override
    public void execute(Server server, GameInterface gameInterface, Socket socket) throws DepositHasReachedMaxLimit, DepositHasAnotherResource {
        gameInterface.placeResource(depositPosition,resourcePosition);
    }
}
