package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.NumMaxPlayersReached;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.SocketConnection;

public class PacketChooseLeaderCardToRemove implements PacketHandler {
    private int ID1;
    private int ID2;

    public PacketChooseLeaderCardToRemove(int ID1, int ID2) {
        this.ID1 = ID1;
        this.ID2 = ID2;
    }


    @Override
    public void execute(GameInterface gameInterface, SocketConnection socketConnection) throws NumMaxPlayersReached {

    }
}
