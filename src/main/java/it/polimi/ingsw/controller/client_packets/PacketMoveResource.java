package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.SocketConnection;

public class PacketMoveResource implements PacketHandler {
    private int position;

    public PacketMoveResource(int position) {
        this.position = position;
    }



    @Override
    public void execute(GameInterface gameInterface, SocketConnection socketConnection) {

    }
}
