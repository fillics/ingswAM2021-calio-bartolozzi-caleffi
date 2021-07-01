package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

/**
 * PacketUsername sent by the Client contains the chosen username
 */
public class PacketUsername implements SetupHandler {
    private final String username;

    /**
     * Class' constructor.
     * @param username is the username of the player.
     */
    @JsonCreator
    public PacketUsername(@JsonProperty("username")String username) {
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    /**
     * Method execute() calls Server methods to check the beginning of the game and if the username is correct.
     */
    @Override
    public void execute(Server server, ClientHandler clientHandler) {

        server.checkUsernameAlreadyTaken(username, clientHandler);

        if(server.getNumPlayers()!=0) server.checkStartOfTheGame();

    }
}
