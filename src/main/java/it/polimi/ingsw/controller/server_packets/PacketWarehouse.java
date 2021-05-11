package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.board.storage.Strongbox;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

import java.util.ArrayList;

public class PacketWarehouse implements ServerPacketHandler{

    private Strongbox strongbox;
    private ArrayList<Deposit> deposits;

    @JsonCreator
    public PacketWarehouse(@JsonProperty("strongbox :")Strongbox strongbox, @JsonProperty("deposits :")ArrayList<Deposit> deposits ) {
        this.strongbox = strongbox;
        this.deposits = deposits;
    }

    @Override
    public void execute(ClientModelView clientModelView) {

    }
}
