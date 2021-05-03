package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.model.GameInterface;

public class PacketPlaceResource implements PacketHandler {
    private int depositPosition;
    private int resourcePosition;

    public PacketPlaceResource(int depositPosition, int resourcePosition) {
        this.depositPosition = depositPosition;
        this.resourcePosition = resourcePosition;
    }

    @Override
    public void execute(GameInterface gameInterface) {

    }
}
