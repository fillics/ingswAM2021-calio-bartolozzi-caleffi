package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.*;
import it.polimi.ingsw.model.board.faithtrack.Cell;
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

    private String username;
    private int idClient;
    private int totalVictoryPoint;
    private Marble[][] table;
    private ArrayList<DevelopmentCard> developmentCards;
    private ArrayList<DevelopmentSpace> developmentSpaces;
    private ArrayList<LeaderCard> leaderCards;
    private ArrayList<Resource> resourceBuffer;
    private ArrayList<ProductionPower> specialProductionPowers;
    private Strongbox strongbox;
    private ArrayList<Deposit> deposits;
    //private ArrayList<Cell> track;
    private ArrayList<Integer> whiteMarbleCardChoice;
    private int posInGame;


    @JsonCreator
    public PacketSetup(@JsonProperty("username") String username, @JsonProperty("idClient") int idClient,@JsonProperty("posInGame") int posInGame, @JsonProperty("total victory points") int totalVictoryPoint,
                       @JsonProperty("market tray") Marble[][] table,@JsonProperty("development grid") ArrayList<DevelopmentCard> developmentCards,
                       @JsonProperty("development spaces") ArrayList<DevelopmentSpace> developmentSpaces, @JsonProperty("leader cards") ArrayList<LeaderCard> leaderCards,
                       @JsonProperty("resource buffer") ArrayList<Resource> resourceBuffer,@JsonProperty("special production powers")  ArrayList<ProductionPower> specialProductionPowers,
                       @JsonProperty("strongbox") Strongbox strongbox,@JsonProperty("deposits") ArrayList<Deposit> deposits, @JsonProperty("white marble leader card's id") ArrayList<Integer> whiteMarbleCardChoice) {
        this.username = username;
        this.idClient = idClient;
        this.posInGame = posInGame;
        this.totalVictoryPoint = totalVictoryPoint;
        this.table = table;
        this.developmentCards = developmentCards;
        this.developmentSpaces = developmentSpaces;
        this.leaderCards = leaderCards;
        this.resourceBuffer = resourceBuffer;
        this.specialProductionPowers = specialProductionPowers;
        this.strongbox = strongbox;
        this.deposits = deposits;
        //this.track = track;
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
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

    public Marble[][] getTable() {
        return table;
    }

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }

    public ArrayList<DevelopmentSpace> getDevelopmentSpaces() {
        return developmentSpaces;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    public ArrayList<Resource> getResourceBuffer() {
        return resourceBuffer;
    }

    public ArrayList<ProductionPower> getSpecialProductionPowers() {
        return specialProductionPowers;
    }

    public Strongbox getStrongbox() {
        return strongbox;
    }

    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    public ArrayList<Integer> getWhiteMarbleCardChoice() {
        return whiteMarbleCardChoice;
    }

    public String getUsername() {
        return username;
    }

    /*public ArrayList<Cell> getTrack() {
        return track;
    }*/

    @Override
    public void execute(Client client) {
        //System.out.println("dentro execute fuori if");
        if(client.getClientState().equals(ClientStates.CREATEMODEL)) {
            LiteBoard liteBoard = new LiteBoard(strongbox,deposits,developmentSpaces,specialProductionPowers);
            LitePlayer litePlayer = new LitePlayer(username, idClient, posInGame, totalVictoryPoint, leaderCards,resourceBuffer, liteBoard, whiteMarbleCardChoice);
            LiteDevelopmentGrid liteDevelopmentGrid = new LiteDevelopmentGrid(developmentCards);
            LiteMarketTray liteMarketTray = new LiteMarketTray(table);
            client.getClientModelView().setDevelopmentGrid(liteDevelopmentGrid);
            client.getClientModelView().setMarketTray(liteMarketTray);
            client.getClientModelView().setLiteBoard(liteBoard);
            client.getClientModelView().setMyPlayer(litePlayer);
            // TODO: 15/05/2021 da togliere questo e chiedere subito all'utente
            System.out.println("Choose your action: \n" + "1. Choose the 2 IDs of the leader cards to remove: ");

            client.setClientState(ClientStates.LEADERSETUP);
        }

    }
}
