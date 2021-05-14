package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.SetupHandler;
import it.polimi.ingsw.server.Server;
import it.polimi.ingsw.server.ClientHandler;


public class PacketUsername implements SetupHandler {
    private String username;
    @JsonCreator
    public PacketUsername(@JsonProperty("username")String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    @Override
    public void execute(Server server, ClientHandler clientHandler) {
        server.addToLobby(username, clientHandler);
        if(server.getNumPlayers()!=0) server.checkStartOfTheGame();
    }
}
