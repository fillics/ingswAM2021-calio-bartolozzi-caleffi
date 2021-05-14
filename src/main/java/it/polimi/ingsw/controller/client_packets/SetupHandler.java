package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.controller.client_packets.*;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PacketUsername.class, name = "USERNAME"),
        @JsonSubTypes.Type(value = PacketNumPlayers.class, name = "NUMOFPLAYERS") })

public interface SetupHandler {
    void execute(Server server, ClientHandler clientHandler);
}
