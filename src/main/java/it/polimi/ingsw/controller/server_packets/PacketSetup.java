package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.*;
import it.polimi.ingsw.client.gui.panels.RemoveLeaderCardPanel;
import it.polimi.ingsw.client.liteclasses.LiteBoard;
import it.polimi.ingsw.client.liteclasses.LiteDevelopmentGrid;
import it.polimi.ingsw.client.liteclasses.LiteMarketTray;
import it.polimi.ingsw.client.liteclasses.LitePlayer;
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

import java.util.ArrayList;

public class PacketSetup implements ServerPacketHandler{

    private final String username;
    private final int idClient;
    private final int totalVictoryPoint;
    private final int faithMarker;
    private final Marble[][] table;
    private final Marble remainingMarble;
    private final ArrayList<DevelopmentCard> developmentCards;
    private final ArrayList<DevelopmentSpace> developmentSpaces;
    private final ArrayList<LeaderCard> leaderCards;
    private final ArrayList<Resource> resourceBuffer;
    private final ArrayList<ProductionPower> specialProductionPowers;
    private final Strongbox strongbox;
    private final ArrayList<Deposit> deposits;
    private final ArrayList<Cell> track;
    private final ArrayList<VaticanReportSection> vaticanReportSections;
    private final ArrayList<Integer> whiteMarbleCardChoice;
    private final int posInGame;
    private final boolean isSingleGame;
    private ArrayList<String> players;

    @JsonCreator
    public PacketSetup(@JsonProperty("username") String username, @JsonProperty("idClient")int idClient, @JsonProperty("posInGame") int posInGame,@JsonProperty("isSingleGame") boolean isSingleGame, @JsonProperty("development grid") ArrayList<DevelopmentCard> developmentCards,
                       @JsonProperty("market tray") Marble[][] table, @JsonProperty("remaining marble") Marble remainingMarble,
                       @JsonProperty("development spaces") ArrayList<DevelopmentSpace> developmentSpaces, @JsonProperty("resource buffer") ArrayList<Resource> resourceBuffer,
                       @JsonProperty("special production powers")  ArrayList<ProductionPower> specialProductionPowers, @JsonProperty("strongbox") Strongbox strongbox,
                       @JsonProperty("deposits") ArrayList<Deposit> deposits, @JsonProperty("white marble leader card's id") ArrayList<Integer> whiteMarbleCardChoice,
                       @JsonProperty("leader cards") ArrayList<LeaderCard> leaderCards, @JsonProperty("track") ArrayList<Cell> track, @JsonProperty("vaticanReportSections") ArrayList<VaticanReportSection> vaticanReportSections,
                       @JsonProperty("players") ArrayList<String> players) {
        this.username = username;
        this.idClient = idClient;
        this.posInGame = posInGame;
        this.isSingleGame=isSingleGame;
        totalVictoryPoint=0;
        faithMarker=0;
        this.table = table;
        this.remainingMarble = remainingMarble;
        this.developmentCards = developmentCards;
        this.developmentSpaces = developmentSpaces;
        this.resourceBuffer = resourceBuffer;
        this.specialProductionPowers = specialProductionPowers;
        this.strongbox = strongbox;
        this.deposits = deposits;
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
        this.leaderCards = leaderCards;
        this.track = track;
        this.vaticanReportSections=vaticanReportSections;
        this.players = players;
    }


    public String getUsername() {
        return username;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getPosInGame() {
        return posInGame;
    }

    public boolean getIsSingleGame() {
        return isSingleGame;
    }

    public int getTotalVictoryPoint() {
        return totalVictoryPoint;
    }

    public int getFaithMarker() {
        return faithMarker;
    }

    public Marble[][] getTable() {
        return table;
    }

    public Marble getRemainingMarble() {
        return remainingMarble;
    }

    public ArrayList<DevelopmentSpace> getDevelopmentSpaces() {
        return developmentSpaces;
    }

    public ArrayList<Resource> getResourceBuffer() {
        return resourceBuffer;
    }

    public ArrayList<Integer> getWhiteMarbleCardChoice() {
        return whiteMarbleCardChoice;
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

    public ArrayList<ProductionPower> getSpecialProductionPowers() {
        return specialProductionPowers;
    }

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public ArrayList<Cell> getTrack() {
        return track;
    }

    public ArrayList<VaticanReportSection> getVaticanReportSections() {
        return vaticanReportSections;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    @Override
    public void execute(Client client) {
        if(client.getClientState().equals(ClientStates.CREATEMODEL)) {

            LiteBoard liteBoard = new LiteBoard(strongbox,deposits,developmentSpaces,specialProductionPowers,track,vaticanReportSections);
            LitePlayer litePlayer = new LitePlayer(username, idClient, posInGame, 0, leaderCards,resourceBuffer, liteBoard, whiteMarbleCardChoice);
            LiteDevelopmentGrid liteDevelopmentGrid = new LiteDevelopmentGrid(developmentCards);
            LiteMarketTray liteMarketTray = new LiteMarketTray(table,remainingMarble);

            client.getClientModelView().setDevelopmentGrid(liteDevelopmentGrid);
            client.getClientModelView().setMarketTray(liteMarketTray);
            client.getClientModelView().setLiteBoard(liteBoard);
            client.getClientModelView().setMyPlayer(litePlayer);
            if(isSingleGame) client.getClientModelView().setSingleGame();
            client.getClientModelView().setSoloActionToken(null);
            client.getClientModelView().setPlayers(players);


            if(client.getViewChoice() == ViewChoice.CLI){
                System.out.println("Your turn position is: " + (client.getClientModelView().getMyPlayer().getPosInGame() + 1));
                System.out.println("Choose your action: \n" + "1. Choose the 2 IDs of the leader cards to remove: ");
            }

            else{
                client.getGui().switchPanels(new RemoveLeaderCardPanel(client.getGui()));
                client.getGui().createMessageFromServer(ConnectionMessages.SELECT_LEADERCARDS.getMessage());
            }

            client.setClientState(ClientStates.LEADERSETUP);
        }

    }
}
