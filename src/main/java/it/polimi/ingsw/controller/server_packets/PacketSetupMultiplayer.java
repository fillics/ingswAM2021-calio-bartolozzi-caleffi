package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.*;
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

public class PacketSetupMultiplayer implements ServerPacketHandler{

    private String username;
    private int idClient;
    private int totalVictoryPoint;
    private int faithMarker;
    private Marble[][] table;
    private Marble remainingMarble;
    private ArrayList<DevelopmentCard> developmentCards;
    private ArrayList<DevelopmentSpace> developmentSpaces;
    private ArrayList<LeaderCard> leaderCards;
    private ArrayList<Resource> resourceBuffer;
    private ArrayList<ProductionPower> specialProductionPowers;
    private Strongbox strongbox;
    private ArrayList<Deposit> deposits;
    private ArrayList<Cell> track;
    private ArrayList<VaticanReportSection> vaticanReportSections;
    private ArrayList<Integer> whiteMarbleCardChoice;
    private int posInGame;


    @JsonCreator
    public PacketSetupMultiplayer(@JsonProperty("username") String username, @JsonProperty("idClient")int idClient, @JsonProperty("posInGame") int posInGame,
                                  @JsonProperty("total victory points") int totalVictoryPoint, @JsonProperty("faithMarker") int faithMarker, @JsonProperty("development grid") ArrayList<DevelopmentCard> developmentCards, @JsonProperty("market tray") Marble[][] table, @JsonProperty("remaining marble") Marble remainingMarble,
                                  @JsonProperty("development spaces") ArrayList<DevelopmentSpace> developmentSpaces, @JsonProperty("resource buffer") ArrayList<Resource> resourceBuffer,
                                  @JsonProperty("special production powers")  ArrayList<ProductionPower> specialProductionPowers, @JsonProperty("strongbox") Strongbox strongbox,
                                  @JsonProperty("deposits") ArrayList<Deposit> deposits, @JsonProperty("white marble leader card's id") ArrayList<Integer> whiteMarbleCardChoice,
                                  @JsonProperty("leader cards") ArrayList<LeaderCard> leaderCards, @JsonProperty("track") ArrayList<Cell> track, @JsonProperty("vaticanReportSections") ArrayList<VaticanReportSection> vaticanReportSections) {
        this.username = username;
        this.idClient = idClient;
        this.posInGame = posInGame;
        this.totalVictoryPoint = totalVictoryPoint;
        this.faithMarker=faithMarker;
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

    //TODO: aggiornare faith marker e black cross negli altri pacchetti ogni volta che vengono modificati
    @Override
    public void execute(Client client) {
        if(client.getClientState().equals(ClientStates.CREATEMODEL)) {
            LiteBoard liteBoard = new LiteBoard(faithMarker,0,strongbox,deposits,developmentSpaces,specialProductionPowers,track,vaticanReportSections);
            LitePlayer litePlayer = new LitePlayer(username, idClient, posInGame, totalVictoryPoint, leaderCards,resourceBuffer, liteBoard, whiteMarbleCardChoice);
            LiteDevelopmentGrid liteDevelopmentGrid = new LiteDevelopmentGrid(developmentCards);
            LiteMarketTray liteMarketTray = new LiteMarketTray(table,remainingMarble);
            client.getClientModelView().setDevelopmentGrid(liteDevelopmentGrid);
            client.getClientModelView().setMarketTray(liteMarketTray);
            client.getClientModelView().setLiteBoard(liteBoard);
            client.getClientModelView().setMyPlayer(litePlayer);

            System.out.println("Your turn position is: " + (client.getClientModelView().getMyPlayer().getPosInGame() + 1));

            System.out.println("Choose your action: \n" + "1. Choose the 2 IDs of the leader cards to remove: ");

            client.setClientState(ClientStates.LEADERSETUP);
        }

    }
}
