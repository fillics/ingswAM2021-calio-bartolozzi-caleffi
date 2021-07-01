package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.cli.CLI;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.OtherPlayersBoardPanel;
import it.polimi.ingsw.client.liteclasses.LiteBoard;
import it.polimi.ingsw.model.board.faithtrack.Cell;
import it.polimi.ingsw.model.board.faithtrack.VaticanReportSection;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.board.storage.Strongbox;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.util.ArrayList;

/**
 * PacketBoardOfAnotherPlayer class is the packet from server to client sent to see the board of another player.
 * It creates a new instance of ClientModelView and fills it with the only attributes of the other player light model that all the players can see.
 */

public class PacketBoardOfAnotherPlayer implements ServerPacketHandler{

    private int faithMarker;
    private ArrayList<Cell> track;
    private ArrayList<VaticanReportSection> vaticanReportSections;
    private final ArrayList<LeaderCard> leaderCards;
    private final Strongbox strongbox;
    private final ArrayList<Deposit> deposits;
    private final ArrayList<DevelopmentSpace> developmentSpaces;
    private final ClientModelView clientModelView;

    /**
     * Class' constructor.
     * @param faithMarker represent the faith marker of the other player.
     * @param strongbox represents the strongbox of the other player.
     * @param deposits represents the deposits of the other player.
     * @param leaderCards represents the leader cards activated of the other player.
     * @param track represents the faith track of the other player.
     * @param vaticanReportSections represents the vatican report sections of the other player.
     * @param developmentSpaces represents the development spaces of the other player.
     */
    @JsonCreator
    public PacketBoardOfAnotherPlayer(@JsonProperty("faithMarker") int faithMarker,
                                      @JsonProperty("track") ArrayList<Cell> track,
                                      @JsonProperty("vaticanReportSections") ArrayList<VaticanReportSection> vaticanReportSections,
                                      @JsonProperty("leadercards") ArrayList<LeaderCard> leaderCards,
                                      @JsonProperty("strongbox") Strongbox strongbox,
                                      @JsonProperty("deposits") ArrayList<Deposit> deposits,
                                      @JsonProperty("devspaces") ArrayList<DevelopmentSpace> developmentSpaces) {
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

    /**
     * Method execute() sets the attributes of the ClientModelView new instance.
     */
    @Override
    public void execute(Client client) {
        LiteBoard liteBoard = clientModelView.getLiteBoard();
        liteBoard.setFaithMarker(faithMarker);
        liteBoard.setTrack(track);
        liteBoard.setVaticanReportSections(vaticanReportSections);
        liteBoard.setDeposits(deposits);
        liteBoard.setStrongbox(strongbox);
        liteBoard.setDevelopmentSpaces(developmentSpaces);
        clientModelView.getMyPlayer().setLeaderCards(leaderCards);

        if(client.getViewChoice().equals(ViewChoice.CLI)){
            CLI cli = new CLI(client, clientModelView);
            cli.printFaithTrack();
            cli.printResourcesLegend();
            cli.printDeposits();
            cli.printStrongbox();
            cli.printDevSpaces();
            cli.printActivatedLeaderCards();
        }
        else{
            client.getGui().switchPanels(new OtherPlayersBoardPanel(client.getGui(), clientModelView));
        }

    }
}
