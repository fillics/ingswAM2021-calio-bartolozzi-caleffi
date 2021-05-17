package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;

public class PacketWinner implements ServerPacketHandler {
    String username;

    @JsonCreator
    public PacketWinner(@JsonProperty("username") String username) {
        this.username = username;
    }

    @Override
    public void execute(Client client) {
        System.out.println("The winner of this game is "+ username);
    }
}
