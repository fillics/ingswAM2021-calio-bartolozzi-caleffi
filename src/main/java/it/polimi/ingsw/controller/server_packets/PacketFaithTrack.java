package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.board.faithtrack.Cell;
import java.util.ArrayList;

public class PacketFaithTrack implements ServerPacketHandler{
    private ArrayList<Cell> track;

    //private ArrayList<Cell> track;

    @JsonCreator
    public PacketFaithTrack(@JsonProperty("faith track :") ArrayList<Cell> track) {
        this.track = track;
    }

    @Override
    public void execute(Client client) {
        client.getClientModelView().getLiteBoard().setTrack(track);
    }

    public ArrayList<Cell> getTrack() {
        return track;
    }
}
