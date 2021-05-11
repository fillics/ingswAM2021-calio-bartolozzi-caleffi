package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private int idPlayer;
    private int totalVictoryPoint;
    private Marble[][] table;
    private DevelopmentCard[][] developmentCards;
    private ArrayList<DevelopmentSpace> developmentSpaces;
    private ArrayList<LeaderCard> leaderCards;
    private ArrayList<Resource> resourceBuffer;
    private ArrayList<ProductionPower> specialProductionPowers;
    private Strongbox strongbox;
    private ArrayList<Deposit> deposits;
    private ArrayList<Integer> whiteMarbleCardChoice;

    @JsonCreator
    public PacketSetup(@JsonProperty("username :") String username,@JsonProperty("idPlayer :") int idPlayer,@JsonProperty("total victory points :") int totalVictoryPoint,
                       @JsonProperty("market tray :") Marble[][] table,@JsonProperty("development grid :") DevelopmentCard[][] developmentCards,
                       @JsonProperty("development spaces :") ArrayList<DevelopmentSpace> developmentSpaces, @JsonProperty("leader cards :") ArrayList<LeaderCard> leaderCards,
                       @JsonProperty("resource buffer :") ArrayList<Resource> resourceBuffer,@JsonProperty("special production powers :")  ArrayList<ProductionPower> specialProductionPowers,
                       @JsonProperty("strongbox :") Strongbox strongbox,@JsonProperty("deposits :") ArrayList<Deposit> deposits, @JsonProperty("white marble leader card's id :") ArrayList<Integer> whiteMarbleCardChoice) {
        this.username = username;
        this.idPlayer = idPlayer;
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
}
