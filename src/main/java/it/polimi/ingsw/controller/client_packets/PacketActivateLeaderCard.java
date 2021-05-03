package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.NumMaxPlayersReached;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.SocketConnection;

public class PacketActivateLeaderCard implements PacketHandler {
    private int ID;

    public PacketActivateLeaderCard(int ID) {
        this.ID = ID;
    }



    @Override
    public void execute(GameInterface gameInterface, SocketConnection socketConnection) throws NumMaxPlayersReached {

    }
}
