package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.client.liteclasses.LiteBoard;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.model.board.faithtrack.Cell;
import it.polimi.ingsw.model.board.faithtrack.VaticanReportSection;

import java.util.ArrayList;

public class PacketFaithTrack implements ServerPacketHandler{

    private final ArrayList<Cell> track;
    private final int faithMarker;
    private final ArrayList<VaticanReportSection> vaticanReportSections;

    @JsonCreator
    public PacketFaithTrack(@JsonProperty("faith track :") ArrayList<Cell> track, @JsonProperty("faithMarker") int faithMarker,
                            @JsonProperty("vaticanReportSections") ArrayList<VaticanReportSection> vaticanReportSections) {
        this.track = track;
        this.faithMarker= faithMarker;
        this.vaticanReportSections=vaticanReportSections;
    }

    @Override
    public void execute(Client client) {
        LiteBoard liteBoard =  client.getClientModelView().getLiteBoard();
        liteBoard.setTrack(track);
        liteBoard.setFaithMarker(faithMarker);
        liteBoard.setVaticanReportSections(vaticanReportSections);

        if(client.getViewChoice().equals(ViewChoice.GUI)) client.getGui().switchPanels(new BoardPanel(client.getGui()));

        else System.out.println("[from server]"+ Constants.ANSI_GREEN+" Faith Track updated!"+Constants.ANSI_RESET);

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
