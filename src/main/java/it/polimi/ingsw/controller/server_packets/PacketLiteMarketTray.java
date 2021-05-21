package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.marbles.Marble;

public class PacketLiteMarketTray implements ServerPacketHandler{

    private final Marble[][] table;
    private Marble remainingMarble;

    @JsonCreator
    public PacketLiteMarketTray(@JsonProperty("market tray")Marble[][] table, @JsonProperty("remainingMarble")Marble remainingMarble) {
        this.table=table;
        this.remainingMarble = remainingMarble;
    }

    @Override
    public void execute(Client client) {
        System.out.println("Market Tray updated");
        client.getClientModelView().getMarketTray().setTable(table);
        client.getClientModelView().getMarketTray().setRemainingMarble(remainingMarble);
    }

    public Marble[][] getTable() {
        return table;
    }

    public Marble getRemainingMarble() {
        return remainingMarble;
    }
}
