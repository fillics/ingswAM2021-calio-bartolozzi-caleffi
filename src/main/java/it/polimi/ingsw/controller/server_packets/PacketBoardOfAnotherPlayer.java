package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.cli.CLI;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.OtherPlayersBoardPanel;
import it.polimi.ingsw.model.board.faithtrack.Cell;
import it.polimi.ingsw.model.board.faithtrack.VaticanReportSection;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.board.storage.Strongbox;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.util.ArrayList;

public class PacketBoardOfAnotherPlayer implements ServerPacketHandler{

    private int faithMarker;
    private ArrayList<Cell> track;
    private ArrayList<VaticanReportSection> vaticanReportSections;
    private final ArrayList<LeaderCard> leaderCards;
    private final Strongbox strongbox;
    private final ArrayList<Deposit> deposits;
    private final ArrayList<DevelopmentSpace> developmentSpaces;
    private ClientModelView clientModelView;
    private CLI cli;
    private GUI gui;


    @JsonCreator
    public PacketBoardOfAnotherPlayer(@JsonProperty("faithMarker") int faithMarker,@JsonProperty("faith track :") ArrayList<Cell> track, @JsonProperty("vaticanReportSections") ArrayList<VaticanReportSection> vaticanReportSections, @JsonProperty("leader cards") ArrayList<LeaderCard> leaderCards, @JsonProperty("strongbox") Strongbox strongbox, @JsonProperty("deposits") ArrayList<Deposit> deposits,@JsonProperty("devspaces") ArrayList<DevelopmentSpace> developmentSpaces) {
        this.faithMarker = faithMarker;
        this.track=track;
        this.vaticanReportSections= vaticanReportSections;
        this.leaderCards = leaderCards;
        this.strongbox = strongbox;
        this.deposits = deposits;
        this.developmentSpaces = developmentSpaces;
        clientModelView = new ClientModelView();
    }

    public int getFaithMarker() {
        return faithMarker;
    }

    public ArrayList<Cell> getTrack() {
        return track;
    }

    public ArrayList<VaticanReportSection> getVaticanReportSections() {
        return vaticanReportSections;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public Strongbox getStrongbox() {
        return strongbox;
    }

    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    public ArrayList<DevelopmentSpace> getDevelopmentSpaces() {
        return developmentSpaces;
    }

    @Override
    public void execute(Client client) {
        if(client.getViewChoice().equals(ViewChoice.CLI)){
            clientModelView.getLiteBoard().setFaithMarker(faithMarker);
            clientModelView.getLiteBoard().setTrack(track);
            clientModelView.getLiteBoard().setVaticanReportSections(vaticanReportSections);
            clientModelView.getLiteBoard().setDeposits(deposits);
            clientModelView.getLiteBoard().setStrongbox(strongbox);
            clientModelView.getLiteBoard().setDevelopmentSpaces(developmentSpaces);
            clientModelView.getMyPlayer().setLeaderCards(leaderCards);
            cli = new CLI(client,clientModelView);
            cli.printFaithTrack();
            cli.printResourcesLegend();
            cli.printDeposits();
            cli.printStrongbox();
            cli.printDevSpaces();
            cli.printActivatedLeaderCards();
        }
        else{
            client.getGui().switchPanels(new OtherPlayersBoardPanel(client.getGui()));
        }

    }
}
