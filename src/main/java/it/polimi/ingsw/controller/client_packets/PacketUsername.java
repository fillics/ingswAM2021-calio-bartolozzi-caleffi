package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.client.SocketClientConnected;

import java.net.Socket;

public class PacketUsername implements PacketHandler {
    private String username;
    @JsonCreator
    public PacketUsername(@JsonProperty("username")String username) {
        this.username = username;
    }

    @Override
    public void execute(GameInterface gameInterface, Socket socketClientConnected) {
        gameInterface.createNewPlayer(username);
        System.out.println("prova usergame");
    }

    public String getUsername() {
        return username;
    }
}
