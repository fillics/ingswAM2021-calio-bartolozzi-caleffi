package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.client_packets.PacketPongFromClient;

public class PacketPingFromServer implements ServerPacketHandler {

    private final ConnectionMessages message;

    @JsonCreator
    public PacketPingFromServer() {
        this.message = ConnectionMessages.PING;
    }

    @Override
    public void execute(Client client) {
        client.serializeAndSend(new PacketPongFromClient());
    }

    public ConnectionMessages getMessage() {
        return message;
    }
}
