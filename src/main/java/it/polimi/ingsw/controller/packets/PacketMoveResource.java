package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.model.GameInterface;

public class PacketMoveResource implements HandlePacket {
    private int position;

    public PacketMoveResource(int position) {
        this.position = position;
    }

    @Override
    public void execute(GameInterface gameInterface) {

    }
}
