package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.client_packets.PacketPongFromClient;

public class PacketPingFromServer implements ServerPacketHandler {

    private final ConnectionMessages message;

    @JsonCreator
    public PacketPingFromServer() {
        this.message = ConnectionMessages.PING;
    }

    @Override
    public void execute(Client client) {
        client.sendPacketToServer(new PacketPongFromClient());
    }

    public ConnectionMessages getMessage() {
        return message;
    }
}
