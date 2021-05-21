package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.board.faithtrack.Cell;
import java.util.ArrayList;

public class PacketFaithTrack implements ServerPacketHandler{

    private ArrayList<Cell> track;
    private int faithMarker;

    @JsonCreator
    public PacketFaithTrack(@JsonProperty("faith track :") ArrayList<Cell> track, @JsonProperty("faithMarker") int faithMarker) {
        this.track = track;
        this.faithMarker= faithMarker;
    }

    @Override
    public void execute(Client client) {
        client.getClientModelView().getLiteBoard().setTrack(track);
        client.getClientModelView().getLiteBoard().setFaithMarker(faithMarker);
    }

    public ArrayList<Cell> getTrack() {
        return track;
    }

    public int getFaithMarker() {
        return faithMarker;
    }
}
