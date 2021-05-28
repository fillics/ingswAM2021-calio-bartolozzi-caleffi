package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;

public class PacketBlackCross implements ServerPacketHandler{
    private int blackCross;

    @JsonCreator
    public PacketBlackCross(@JsonProperty("blackCross") int blackCross){
        this.blackCross= blackCross;
    }

    @Override
    public void execute(Client client) {
        client.getClientModelView().getLiteBoard().setBlackCross(blackCross);
    }

    public int getBlackCross() {
        return blackCross;
    }
}
