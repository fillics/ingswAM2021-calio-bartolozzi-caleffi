package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.CLI;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.server.ClientProxy;

public class PacketReconnection implements ServerPacketHandler{

    private final ConnectionMessages message;
    private final ClientProxy clientProxy;

    @JsonCreator
    public PacketReconnection(@JsonProperty("clientProxy") ClientProxy clientProxy) {
        message = ConnectionMessages.PLAYER_RECONNECTED;
        this.clientProxy = clientProxy;
    }


    // TODO: 28/05/2021
    @Override
    public void execute(Client client) {
        Constants.printConnectionMessage(message);
        client.setClientState(clientProxy.getClientStates());
        client.setClientModelView(clientProxy.getClientModelView());
        client.getClientOperationHandler().setClientModelView(clientProxy.getClientModelView());


        if(client.getChoiceInterface()==1) {
            client.setInterface(new CLI(client, clientProxy.getClientModelView()));
        }
        else {
            client.setInterface(new GUI(client, clientProxy.getClientModelView()));
        }


        switch (client.getClientState()){
            case CREATEMODEL -> {
                System.out.println("Choose your action: \n" + "1. Choose the 2 IDs of the leader cards to remove: ");
                client.setClientState(ClientStates.LEADERSETUP);
            }

            case RESOURCESETUP -> {
                if (client.getClientModelView().getMyPlayer().getPosInGame() != 0) {
                    client.setClientState(ClientStates.RESOURCESETUP);
                    System.out.println("Choose your action:\n1. Choose your optional resources");
                } else {
                    client.setClientState(ClientStates.GAMESTARTED);
                    System.out.println(Constants.menu);
                }
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
