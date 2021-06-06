package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.constants.Constants;
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

        if(client.getClientState() == ClientStates.RESOURCESETUP){
            if(client.getViewChoice().equals(ViewChoice.GUI)){
                client.getGui().switchPanels(new BoardPanel(client.getGui()));
            }
            else System.out.println("[from server]"+ Constants.ANSI_GREEN+" Warehouse updated!"+Constants.ANSI_RESET);
            client.setClientState(ClientStates.GAMESTARTED);
        }



    }

    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    public Strongbox getStrongbox() {
        return strongbox;
    }
}
