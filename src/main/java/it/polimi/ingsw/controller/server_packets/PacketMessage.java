package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.ConnectionMessages;

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
            client.setClientState(ClientStates.NUMPLAYERS);
        }
        else if (message.equals(ConnectionMessages.TAKEN_NICKNAME)) {
            Constants.printConnectionMessage(message);
            client.setClientState(ClientStates.USERNAME);
        }
        else if(message.equals(ConnectionMessages.WAITING_PEOPLE)){
            Constants.printConnectionMessage(message);
            client.setClientState(ClientStates.WAITPLAYERS);
        }
        else if (message.equals(ConnectionMessages.GAME_IS_STARTING)) {
            Constants.printConnectionMessage(message);
            client.setClientState(ClientStates.CREATEMODEL);
        }
        else if (message.equals(ConnectionMessages.YOUR_TURN)) {
            Constants.printConnectionMessage(message);
            client.setClientState(ClientStates.GAMESTARTED);
        }
        else {
            throw new IllegalStateException("Unexpected value: " + message);
        }
    }

    public ConnectionMessages getMessage() {
        return message;
    }


}
