package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;

public class PacketConnectionMessages implements ServerPacketHandler {

    private ConnectionMessages message;

    @JsonCreator
    public PacketConnectionMessages(@JsonProperty("message") ConnectionMessages message) {
        this.message = message;
    }


    /**
     * Method handleSetupMessage handles the messages that the server sends.
     * According to them, it calls the right methods.
     */
    @Override
    public void execute(Client client) {
        switch (message){

            case USERNAME_VALID, IMPOSSIBLEMOVE, IMPOSSIBLEENDTURN -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)){
                    Constants.printConnectionMessage(message);
                }
            }


            case INSERT_NUMBER_OF_PLAYERS -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)){
                    Constants.printConnectionMessage(ConnectionMessages.LOBBY_MASTER);
                    Constants.printConnectionMessage(message);
                }
                else {
                    client.getGui().switchPanels(client.getGui().getPanels().get(2));
                }
                client.setClientState(ClientStates.NUMPLAYERS);


            }

            case TAKEN_NICKNAME -> {
                Constants.printConnectionMessage(message);
                client.setClientState(ClientStates.USERNAME);
            }

            case WAITING_PEOPLE -> {
                Constants.printConnectionMessage(message);
                client.setClientState(ClientStates.WAITPLAYERS);
            }

            case GAME_IS_STARTING -> {
                Constants.printConnectionMessage(message);
                client.setClientState(ClientStates.CREATEMODEL);
            }

            case YOUR_TURN, DISCARDDEVCARD, BLACKCROSS1, BLACKCROSS2, BLACKCROSSUPDATE -> {
                Constants.printConnectionMessage(message);
                client.setClientState(ClientStates.GAMESTARTED);
            }

            default ->  throw new IllegalStateException("Unexpected value: " + message);

        }
    }

    public ConnectionMessages getMessage() {
        return message;
    }


}
