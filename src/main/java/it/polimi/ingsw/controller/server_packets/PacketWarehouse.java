package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.model.board.storage.Deposit;
import it.polimi.ingsw.model.board.storage.Strongbox;

import java.util.ArrayList;

public class PacketWarehouse implements ServerPacketHandler{

    private final Strongbox strongbox;
    private final ArrayList<Deposit> deposits;

    @JsonCreator
    public PacketWarehouse(@JsonProperty("strongbox :")Strongbox strongbox, @JsonProperty("deposits :")ArrayList<Deposit> deposits ) {
        this.strongbox = strongbox;
        this.deposits = deposits;
    }

    @Override
    public void execute(Client client) {
        client.getClientModelView().getLiteBoard().setDeposits(deposits);
        client.getClientModelView().getLiteBoard().setStrongbox(strongbox);
    }

    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    public Strongbox getStrongbox() {
        return strongbox;
    }
}
