package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.model.marbles.Marble;

/**
 * PacketLiteMarketTray represents the Lite Market Tray class, for the lite model of the client
 */
public class PacketLiteMarketTray implements ServerPacketHandler{

    private final Marble[][] table;
    private final Marble remainingMarble;

    /**
     * Class' constructor.
     * @param table represents the market.
     * @param remainingMarble represent the remaining marble of the market.
     */
    @JsonCreator
    public PacketLiteMarketTray(@JsonProperty("market tray")Marble[][] table, @JsonProperty("remainingMarble")Marble remainingMarble) {
        this.table=table;
        this.remainingMarble = remainingMarble;
    }

    /**
     * Method execute() updates the LiteMarketTray class attributes.
     */
    @Override
    public void execute(Client client) {
        if(client.getViewChoice().equals(ViewChoice.CLI)) System.out.println("[from server]"+ Constants.ANSI_GREEN+" Market Tray updated!"+Constants.ANSI_RESET);
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
