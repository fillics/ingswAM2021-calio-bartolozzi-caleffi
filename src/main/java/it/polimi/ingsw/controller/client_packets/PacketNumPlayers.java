package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;


/**
 * PacketNumPlayers contains the number of players that the lobby master wants to start the game
 */
public class PacketNumPlayers implements SetupHandler {
    private final int numof_players;

    /**
     * Class' constructor.
     * @param numof_players is the numerb of players desired by the first player entering in the game.
     */
    @JsonCreator
    public PacketNumPlayers(@JsonProperty("Numof_players")int numof_players) {
        this.numof_players = numof_players;
    }

    /**
     * Method execute() calls Server methods to handle the beginning of the game.
     */
    @Override
    public void execute(Server server, ClientHandler clientHandler) {
        server.setNumPlayers(numof_players);

        if(numof_players > server.getLobby().size()){
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.WAITING_PEOPLE));
        }
        else {
            server.checkStartOfTheGame();
        }
    }

    public int getNumof_players() { return numof_players; }
}
