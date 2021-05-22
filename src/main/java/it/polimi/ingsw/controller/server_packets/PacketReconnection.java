package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;

public class PacketReconnection implements ServerPacketHandler{

    private final ConnectionMessages message;
    private ClientModelView clientModelView;

    @JsonCreator
    public PacketReconnection() {
        message = ConnectionMessages.PLAYER_RECONNECTED;
    }


    @Override
    public void execute(Client client) {
        Constants.printConnectionMessage(message);
    }

    public ConnectionMessages getMessage() {
        return message;
    }

    public ClientModelView getClientModelView() {
        return clientModelView;
    }
}
