package it.polimi.ingsw.controller.server_packets;

import it.polimi.ingsw.client.Client;

public class PacketNewPositionInGame  implements ServerPacketHandler {

    private final int posInGame;

    public PacketNewPositionInGame(int posInGame) {
        this.posInGame = posInGame;
    }

    @Override
    public void execute(Client client) {
        client.getClientModelView().getMyPlayer().setPosInGame(posInGame);
    }
}
