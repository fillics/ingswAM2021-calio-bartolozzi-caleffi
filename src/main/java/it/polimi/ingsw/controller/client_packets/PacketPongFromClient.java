package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

public class PacketPongFromClient implements SetupHandler{
    private final ConnectionMessages message;

    @JsonCreator
    public PacketPongFromClient() {
        this.message = ConnectionMessages.PONG;
    }


    public ConnectionMessages getMessage() {
        return message;
    }

    @Override
    public void execute(Server server, ClientHandler clientHandler) {
    }
}
