package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.SocketClientConnected;

public class PacketDiscardLeaderCard implements PacketHandler {
    private int ID;

    public PacketDiscardLeaderCard(int ID) {
        this.ID = ID;
    }


    @Override
    public void execute(GameInterface gameInterface, SocketClientConnected socketClientConnected){

    }
}
