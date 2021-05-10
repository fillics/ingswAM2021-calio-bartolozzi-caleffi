package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.board.storage.Strongbox;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

import java.util.ArrayList;

public class PacketLiteBoard implements ServerPacketHandler{

    private Strongbox strongbox;
    private ArrayList<Deposit> deposits;
    private ArrayList<DevelopmentSpace> developmentSpaces;
    private ArrayList<ProductionPower> specialProductionPowers;

    @JsonCreator
    public PacketLiteBoard(@JsonProperty("strongbox :")Strongbox strongbox, @JsonProperty("deposits :")ArrayList<Deposit> deposits, @JsonProperty("development spaces :") ArrayList<DevelopmentSpace> developmentSpaces,@JsonProperty("special production powers :") ArrayList<ProductionPower> specialProductionPowers) {
        this.strongbox = strongbox;
        this.deposits = deposits;
        this.developmentSpaces = developmentSpaces;
        this.specialProductionPowers = specialProductionPowers;
    }
}
