package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.*;
import it.polimi.ingsw.model.board.resources.Resource;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.board.storage.Strongbox;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyDiscount;
import it.polimi.ingsw.model.cards.leadercards.ConcreteStrategyMarble;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;
import it.polimi.ingsw.model.cards.leadercards.LeaderCardType;
import it.polimi.ingsw.model.marbles.Marble;

import java.util.ArrayList;

public class PacketSetup implements ServerPacketHandler{

    private final String username;
    private final int idClient;
    private final int totalVictoryPoint;
    private final Marble[][] table;
    private final ArrayList<DevelopmentCard> developmentCards;
    private final ArrayList<DevelopmentSpace> developmentSpaces;
    private final ArrayList<LeaderCard> leaderCards;
    private final ArrayList<Resource> resourceBuffer;
    private final ArrayList<ProductionPower> specialProductionPowers;
    private final Strongbox strongbox;
    private final ArrayList<Deposit> deposits;
    private final ArrayList<Integer> whiteMarbleCardChoice;


    @JsonCreator
    public PacketSetup(@JsonProperty("username") String username, @JsonProperty("idClient") int idClient,@JsonProperty("total victory points") int totalVictoryPoint,
                       @JsonProperty("market tray") Marble[][] table,@JsonProperty("development grid") ArrayList<DevelopmentCard> developmentCards,
                       @JsonProperty("development spaces") ArrayList<DevelopmentSpace> developmentSpaces, @JsonProperty("leader cards") ArrayList<LeaderCard> leaderCards,
                       @JsonProperty("resource buffer") ArrayList<Resource> resourceBuffer,@JsonProperty("special production powers")  ArrayList<ProductionPower> specialProductionPowers,
                       @JsonProperty("strongbox") Strongbox strongbox,@JsonProperty("deposits") ArrayList<Deposit> deposits, @JsonProperty("white marble leader card's id") ArrayList<Integer> whiteMarbleCardChoice) {
        this.username = username;
        this.idClient = idClient;
        this.totalVictoryPoint = totalVictoryPoint;
        this.table = table;
        this.developmentCards = developmentCards;
        this.developmentSpaces = developmentSpaces;
        this.leaderCards = leaderCards;
        this.resourceBuffer = resourceBuffer;
        this.specialProductionPowers = specialProductionPowers;
        this.strongbox = strongbox;
        this.deposits = deposits;
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
    }

    public int getIdClient() {
        return idClient;
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

    @Override
    public void execute(ClientModelView clientModelView) {
        LiteBoard liteBoard = new LiteBoard(strongbox,deposits,developmentSpaces,specialProductionPowers);
        LitePlayer litePlayer = new LitePlayer(username, idClient,totalVictoryPoint,leaderCards,resourceBuffer,liteBoard,whiteMarbleCardChoice);
        LiteDevelopmentGrid liteDevelopmentGrid = new LiteDevelopmentGrid(developmentCards);
        LiteMarketTray liteMarketTray = new LiteMarketTray(table);
        clientModelView.setDevelopmentGrid(liteDevelopmentGrid);
        clientModelView.setMarketTray(liteMarketTray);
        clientModelView.setLiteBoard(liteBoard);
        clientModelView.setMyPlayer(litePlayer);
    }
}
