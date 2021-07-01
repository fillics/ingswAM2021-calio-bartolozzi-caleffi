package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.client.liteclasses.LiteBoard;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.model.board.faithtrack.Cell;
import it.polimi.ingsw.model.board.faithtrack.VaticanReportSection;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.board.storage.Strongbox;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.model.marbles.Marble;
import it.polimi.ingsw.model.singleplayer.SoloActionToken;

import java.util.ArrayList;

public class PacketUpdate implements ServerPacketHandler{


    private  SoloActionToken soloActionToken;
    private int blackCross;

    private ArrayList<Cell> track;
    private int faithMarker;
    private ArrayList<VaticanReportSection> vaticanReportSections;

    private  ArrayList<DevelopmentCard> developmentCards;


    @JsonCreator
    public PacketUpdate(@JsonProperty("soloActionToken") SoloActionToken soloActionToken,
                        @JsonProperty("blackCross") int blackCross,
                        @JsonProperty("faith track :")ArrayList<Cell> track,
                        @JsonProperty("faithMarker") int faithMarker,
                        @JsonProperty("vaticanReportSections") ArrayList<VaticanReportSection> vaticanReportSections,
                        @JsonProperty("development grid :") ArrayList<DevelopmentCard> developmentCards) {

        this.soloActionToken = soloActionToken;
        this.blackCross = blackCross;
        this.track = track;
        this.faithMarker = faithMarker;
        this.vaticanReportSections = vaticanReportSections;
        this.developmentCards = developmentCards;
    }

    public SoloActionToken getSoloActionToken() {
        return soloActionToken;
    }

    public int getBlackCross() {
        return blackCross;
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

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    @Override
    public void execute(Client client) {
        client.getClientModelView().setSoloActionToken(soloActionToken);
        client.getClientModelView().getLiteBoard().setBlackCross(blackCross);
        client.getClientModelView().getDevelopmentGrid().setDevelopmentCards(developmentCards);

        LiteBoard liteBoard =  client.getClientModelView().getLiteBoard();
        liteBoard.setTrack(track);
        liteBoard.setFaithMarker(faithMarker);
        liteBoard.setVaticanReportSections(vaticanReportSections);

        if(client.getViewChoice().equals(ViewChoice.GUI)) {
            client.getGui().switchPanels(new BoardPanel(client.getGui()));
        }
        else {
            System.out.println("[from server]"+ Constants.ANSI_GREEN+" Faith Track updated!"+Constants.ANSI_RESET);
            System.out.println("[from server]"+Constants.ANSI_GREEN+" Development grid updated!"+Constants.ANSI_RESET);
        }

    }
}
