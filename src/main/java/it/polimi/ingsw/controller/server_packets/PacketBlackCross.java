package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;

public class PacketBlackCross implements ServerPacketHandler{
    private final int blackCross;

    /**
     * Class' constructor.
     * @param blackCross is the value of the black cross.
     */
    @JsonCreator
    public PacketBlackCross(@JsonProperty("blackCross") int blackCross){
        this.blackCross= blackCross;
    }

    /**
     * Method execute() updates the black cross value in LiteBoard class.
     */
    @Override
    public void execute(Client client) {
        client.getClientModelView().getLiteBoard().setBlackCross(blackCross);
    }

    public int getBlackCross() {
        return blackCross;
    }
}
