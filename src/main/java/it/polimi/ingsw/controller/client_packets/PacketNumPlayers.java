package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.controller.SetupHandler;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketNumPlayers implements SetupHandler {
    private int numof_players;

    @JsonCreator
    public PacketNumPlayers(@JsonProperty("number of players: ")int numof_players) {
        this.numof_players = numof_players;
    }

    public int getNumof_players() { return numof_players; }

    @Override
    public void execute(Server server, ClientHandler clientHandler) {
        System.out.println("dentro execute");
        server.setNumPlayers(numof_players);

        if(numof_players > server.getLobby().size()){
            clientHandler.sendMessageToClient(ConnectionMessages.WAITING_PEOPLE);
        }
        else server.createMatch();
    }
}
