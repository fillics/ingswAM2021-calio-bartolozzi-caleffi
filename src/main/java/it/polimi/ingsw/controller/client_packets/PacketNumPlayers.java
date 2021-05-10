package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


public class PacketNumPlayers implements ClientPacketHandler {
    private int numof_players;

    @JsonCreator
    public PacketNumPlayers(@JsonProperty("number of players: ")int numof_players) {
        this.numof_players = numof_players;
    }

    //TODO: Decidere se aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        server.setNumPlayers(numof_players);


        if(numof_players > server.getLobby().size()){
            System.out.println("NumPlayers Settati: "+server.getNumPlayers());
            System.out.println("Dimensione Lobby: "+server.getLobby().size());
            clientHandler.waitingPeople();
        }
        else server.createMatch();

    }

    public int getNumof_players() { return numof_players; }
}
