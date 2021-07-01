package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.controller.Packet;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

/**
 * SetupHandler interface defines an interface for setup packets from Client to Server.
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PacketUsername.class, name = "USERNAME"),
        @JsonSubTypes.Type(value = PacketPongFromClient.class, name = "PONG"),
        @JsonSubTypes.Type(value = PacketNumPlayers.class, name = "NUMOFPLAYERS") })

public interface SetupHandler extends Packet {
    /**
     * Method execute() is called after the deserialization of the packet in ClientHandler.
     * It calls methods of Server and ClientHandler that handle the setup of the game.
     */
    void execute(Server server, ClientHandler clientHandler);
}
