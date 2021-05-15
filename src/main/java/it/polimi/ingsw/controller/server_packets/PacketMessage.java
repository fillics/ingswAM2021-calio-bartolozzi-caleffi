package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientState;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.lang.constant.Constable;

public class PacketMessage implements ServerPacketHandler {

    private ConnectionMessages message;

    @JsonCreator
    public PacketMessage(@JsonProperty("message") ConnectionMessages message) {
        this.message = message;
    }


    /**
     * Method handleSetupMessage handles the messages that the server sends. According to them, it calls the right methods.
     */
    @Override
    public void execute(Client client) {
        if (message.equals(ConnectionMessages.INSERT_NUMBER_OF_PLAYERS)) {
            Constants.printConnectionMessage(ConnectionMessages.LOBBY_MASTER);
            Constants.printConnectionMessage(message);
            client.setClientState(ClientState.NUMPLAYERS);
        }
        else if (message.equals(ConnectionMessages.TAKEN_NICKNAME)) {
            Constants.printConnectionMessage(message);
            client.setClientState(ClientState.USERNAME);
        }
        else if(message.equals(ConnectionMessages.WAITING_PEOPLE)){
            Constants.printConnectionMessage(message);
            client.setClientState(ClientState.WAITPLAYERS);
        }
        else if (message.equals(ConnectionMessages.GAME_IS_STARTING)) {
            Constants.printConnectionMessage(message);
            client.setClientState(ClientState.CREATEMODEL);
        }
        else {
            throw new IllegalStateException("Unexpected value: " + message);
        }
    }

    public ConnectionMessages getMessage() {
        return message;
    }


}
