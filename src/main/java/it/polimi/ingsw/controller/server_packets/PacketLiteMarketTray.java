package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.LiteMarketTray;
import it.polimi.ingsw.model.marbles.Marble;

public class PacketLiteMarketTray implements ServerPacketHandler{

    private Marble[][] table;

    @JsonCreator
    public PacketLiteMarketTray(@JsonProperty("market tray :")Marble[][] table) {
        this.table=table;
    }
}
