package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.model.GameInterface;

public class PacketActivateLeaderCard implements HandlePacket {
    private int ID;

    public PacketActivateLeaderCard(int ID) {
        this.ID = ID;
    }

    @Override
    public void execute(GameInterface gameInterface) {

    }
}
