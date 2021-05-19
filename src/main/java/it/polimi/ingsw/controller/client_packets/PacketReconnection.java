package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketReconnection implements SetupHandler {

    private int choice;
    @JsonCreator
    public PacketReconnection(@JsonProperty("choice")int choice) {
        this.choice = choice;
    }

    @Override
    public void execute(Server server, ClientHandler clientHandler) {

    }

    public int getChoice() {
        return choice;
    }
}
