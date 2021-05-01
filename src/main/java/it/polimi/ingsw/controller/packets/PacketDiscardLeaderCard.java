package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.model.GameInterface;

public class PacketDiscardLeaderCard implements HandlePacket {
    private int ID;

    public PacketDiscardLeaderCard(int ID) {
        this.ID = ID;
    }

    @Override
    public void execute(GameInterface gameInterface) {

    }
}
