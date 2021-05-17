package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
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


        if(client.getClientState().equals(ClientStates.RESOURCESETUP)) client.setClientState(ClientStates.GAMESTARTED);
        System.out.println("Choose one of the operations you can do:\nPress 0 to quit");
        System.out.println("1: Activate a Leader Card\n" +
                "2: Buy a Development Card\n" +
                "3: Choose Discount\n" +
                "4: Use production powers\n" +
                "5: Discard a Leader Card\n" +
                "6: Move one of you resources\n" +
                "7: Place one of your resources\n" +
                "8: Take resources from the market\n" +
                "9: End Turn\n");

    }

    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    public Strongbox getStrongbox() {
        return strongbox;
    }
}
