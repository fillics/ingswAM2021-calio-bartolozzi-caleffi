package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.client.SocketClientConnected;

public class PacketActivateLeaderCard implements PacketHandler {
    private int ID;

    public PacketActivateLeaderCard(int ID) {
        this.ID = ID;
    }



    @Override
    public void execute(GameInterface gameInterface, SocketClientConnected socketClientConnected) {

    }
}
