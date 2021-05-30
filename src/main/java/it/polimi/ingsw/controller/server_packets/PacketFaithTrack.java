package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.board.faithtrack.Cell;
import it.polimi.ingsw.model.board.faithtrack.VaticanReportSection;

import java.util.ArrayList;

public class PacketFaithTrack implements ServerPacketHandler{

    private ArrayList<Cell> track;
    private int faithMarker;
    private ArrayList<VaticanReportSection> vaticanReportSections;

    @JsonCreator
    public PacketFaithTrack(@JsonProperty("faith track :") ArrayList<Cell> track, @JsonProperty("faithMarker") int faithMarker, @JsonProperty("vaticanReportSections") ArrayList<VaticanReportSection> vaticanReportSections) {
        this.track = track;
        this.faithMarker= faithMarker;
        this.vaticanReportSections=vaticanReportSections;
    }

    @Override
    public void execute(Client client) {
        //System.out.println("new faith marker: "+ faithMarker);
        client.getClientModelView().getLiteBoard().setTrack(track);
        client.getClientModelView().getLiteBoard().setFaithMarker(faithMarker);
        client.getClientModelView().getLiteBoard().setVaticanReportSections(vaticanReportSections);
    }

    public ArrayList<Cell> getTrack() {
        return track;
    }

    public int getFaithMarker() {
        return faithMarker;
    }

    public ArrayList<VaticanReportSection> getVaticanReportSections() {
        return vaticanReportSections;
    }
}
