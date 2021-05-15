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
    private ArrayList<Integer> whiteMarbleCardChoice;
    private int posInGame;
    private int numOfPlayers;
    private int firstPosition;


    @JsonCreator
    public PacketSetup(@JsonProperty("username") String username, @JsonProperty("idClient") int idClient,@JsonProperty("posInGame") int posInGame,
                       @JsonProperty("numOfPlayers") int numOfPlayers, @JsonProperty("first position") int firstPosition, @JsonProperty("total victory points") int totalVictoryPoint,
                       @JsonProperty("market tray") Marble[][] table,@JsonProperty("development grid") ArrayList<DevelopmentCard> developmentCards,
                       @JsonProperty("development spaces") ArrayList<DevelopmentSpace> developmentSpaces, @JsonProperty("leader cards") ArrayList<LeaderCard> leaderCards,
                       @JsonProperty("resource buffer") ArrayList<Resource> resourceBuffer,@JsonProperty("special production powers")  ArrayList<ProductionPower> specialProductionPowers,
                       @JsonProperty("strongbox") Strongbox strongbox,@JsonProperty("deposits") ArrayList<Deposit> deposits, @JsonProperty("white marble leader card's id") ArrayList<Integer> whiteMarbleCardChoice) {
        this.username = username;
        this.idClient = idClient;
        this.posInGame = posInGame;
        this.numOfPlayers = numOfPlayers;
        this.firstPosition = firstPosition;
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

    public int getPosInGame() {
        return posInGame;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public int getFirstPosition() {
        return firstPosition;
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
    public void execute(Client client) {
        if(client.getClientState().equals(ClientState.CREATEMODEL)) {
            LiteBoard liteBoard = new LiteBoard(strongbox,deposits,developmentSpaces,specialProductionPowers);
            LitePlayer litePlayer = new LitePlayer(username, idClient,posInGame, totalVictoryPoint,leaderCards,resourceBuffer,liteBoard,whiteMarbleCardChoice);
            LiteDevelopmentGrid liteDevelopmentGrid = new LiteDevelopmentGrid(developmentCards);
            LiteMarketTray liteMarketTray = new LiteMarketTray(table);
            client.getClientModelView().setDevelopmentGrid(liteDevelopmentGrid);
            client.getClientModelView().setMarketTray(liteMarketTray);
            client.getClientModelView().setLiteBoard(liteBoard);
            client.getClientModelView().setMyPlayer(litePlayer);
            client.getClientModelView().setFirstPosition(firstPosition);
            client.getClientModelView().setNumOfPlayers(numOfPlayers);
            System.out.println("Choose your action: 1.Choose the 2 IDs of the leader cards to remove: ");
            for (LeaderCard leaderCard : client.getClientModelView().getMyPlayer().getLeaderCards()) {
                System.out.println(leaderCard.getId()); //TODO: sarà da commentare
            }
            client.setClientState(ClientState.LEADERSETUP);
        }

    }
}
