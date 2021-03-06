package it.polimi.ingsw.controller.client_packets.cheatpackets;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.controller.Packet;
import it.polimi.ingsw.model.gameinterfaces.CheatGameInterface;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "command")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ResourcesInStrongboxCheatPacket.class, name = "RESOURCE_CHEAT"),
        @JsonSubTypes.Type(value = FaithMarkerCheatPacket.class, name = "FAITHMARKER_CHEAT")})

public interface CheatClientPacketHandler extends Packet {
    void execute(Server server, CheatGameInterface gameInterface, ClientHandler clientHandler);
}
