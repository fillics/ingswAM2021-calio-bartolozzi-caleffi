package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.SocketConnection;

public class PacketPlaceResource implements PacketHandler {
    private int depositPosition;
    private int resourcePosition;

    public PacketPlaceResource(int depositPosition, int resourcePosition) {
        this.depositPosition = depositPosition;
        this.resourcePosition = resourcePosition;
    }



    @Override
    public void execute(GameInterface gameInterface, SocketConnection socketConnection){

    }
}
