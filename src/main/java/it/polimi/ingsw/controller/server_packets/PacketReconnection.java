package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.cli.CLI;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.panels.pregamepanels.AdditionalResourcePanel;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.client.gui.panels.pregamepanels.RemoveLeaderCardPanel;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.server.ClientProxy;

/**
 * Packet used when a player reconnected to the game. It takes all the information from the ClientProxy and sets them
 * in the client model view of the player
 */
public class PacketReconnection implements ServerPacketHandler{

    private final ConnectionMessages message;
    private final ClientProxy clientProxy;

    @JsonCreator
    public PacketReconnection(@JsonProperty("clientProxy") ClientProxy clientProxy) {
        message = ConnectionMessages.PLAYER_RECONNECTED;
        this.clientProxy = clientProxy;
    }


   @Override
    public void execute(Client client) {

        client.setClientState(clientProxy.getClientStates());
        client.setClientModelView(clientProxy.getClientModelView());
        client.getClientModelView().setSingleGame();

        if(client.getViewChoice().equals(ViewChoice.CLI)) {
            client.getCliOperationHandler().setClientModelView(clientProxy.getClientModelView());
            client.getCliOperationHandler().setViewInterface(new CLI(client, clientProxy.getClientModelView()));
            Constants.printConnectionMessage(message);
        }


        switch (client.getClientState()){
            case CREATEMODEL -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)) {
                    System.out.println("Choose your action: \n" + "1. Choose the 2 IDs of the leader cards to remove: ");
                }
                else client.getGui().switchPanels(new RemoveLeaderCardPanel(client.getGui()));
                client.setClientState(ClientStates.LEADERSETUP);
            }

            case RESOURCESETUP -> {
                if (client.getClientModelView().getMyPlayer().getPosInGame() != 0) {
                    if(client.getViewChoice().equals(ViewChoice.CLI)) {
                        System.out.println("Choose your action:\n1. Choose your optional resources");
                    }
                    else client.getGui().switchPanels(new AdditionalResourcePanel(client.getGui()));

                    client.setClientState(ClientStates.RESOURCESETUP);

                } else {
                    if(client.getViewChoice().equals(ViewChoice.CLI)) {
                        System.out.println(Constants.menu);
                    }
                    else client.getGui().switchPanels(new BoardPanel(client.getGui()));
                    client.setClientState(ClientStates.GAMESTARTED);
                }
            }
            case GAMESTARTED -> {
                if (client.getViewChoice().equals(ViewChoice.GUI)) client.getGui().switchPanels(new BoardPanel(client.getGui()));
            }

        }

    }

    public ConnectionMessages getMessage() {
        return message;
    }

    public ClientProxy getClientProxy() {
        return clientProxy;
    }
}
