package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;

public class PacketEndGameStarted implements ServerPacketHandler {
    String username;

    @JsonCreator
    public PacketEndGameStarted(@JsonProperty("username") String username) {
        this.username = username;
    }

    @Override
    public void execute(Client client) {
        System.out.println(username + "finished the game, all the players must finish their turn and then it'll determined the winner!");
    }
}
