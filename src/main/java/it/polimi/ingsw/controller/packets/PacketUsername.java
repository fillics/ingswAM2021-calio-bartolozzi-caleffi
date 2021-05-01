package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.exceptions.NumMaxPlayersReached;
import it.polimi.ingsw.model.GameInterface;

public class PacketUsername implements HandlePacket {
    private String username;

    public PacketUsername(String username) {
        this.username = username;
    }

    @Override
    public void execute(GameInterface gameInterface) throws NumMaxPlayersReached {
        gameInterface.createNewPlayer(username);
    }
}
