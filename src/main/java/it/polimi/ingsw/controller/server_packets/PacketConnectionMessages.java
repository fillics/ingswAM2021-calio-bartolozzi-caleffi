package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;

import javax.swing.*;

public class PacketConnectionMessages implements ServerPacketHandler {

    private ConnectionMessages message;

    @JsonCreator
    public PacketConnectionMessages(@JsonProperty("message") ConnectionMessages message) {
        this.message = message;
    }


    @Override
    public void execute(Client client) {
        switch (message){

            case USERNAME_VALID, IMPOSSIBLEMOVE -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)){
                    Constants.printConnectionMessage(message);
                }
                else{
                    //JOptionPane.showMessageDialog(client.getGui().getjFrame(), message);
                }
            }

            case IMPOSSIBLEENDTURN -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)){
                    Constants.printConnectionMessage(message);
                }
                else{
                    JOptionPane.showMessageDialog(client.getGui().getjFrame(), message.getMessage());
                }
            }
            case INSERT_NUMBER_OF_PLAYERS -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)){
                    Constants.printConnectionMessage(ConnectionMessages.LOBBY_MASTER);
                    Constants.printConnectionMessage(message);
                }
                else {
                    client.getGui().createMessageFromServer(ConnectionMessages.LOBBY_MASTER_GUI.getMessage());
                    client.getGui().switchPanels(client.getGui().getNumPlayersPanel());
                }
                client.setClientState(ClientStates.NUMPLAYERS);


            }

            case TAKEN_NICKNAME -> {

                if(client.getViewChoice().equals(ViewChoice.CLI)){
                    Constants.printConnectionMessage(message);
                }
                else {
                    JOptionPane.showMessageDialog(client.getGui().getjFrame(), message.getMessage());
                }

                client.setClientState(ClientStates.USERNAME);
            }

            case WAITING_PEOPLE -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)) {
                    Constants.printConnectionMessage(message);
                }
                else{
                    client.getGui().createMessageFromServer(ConnectionMessages.WAITING_PEOPLE.getMessage());
                }

                client.setClientState(ClientStates.WAITPLAYERS);
            }

            case GAME_IS_STARTING -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)) {
                    Constants.printConnectionMessage(message);
                }
                else{
                    client.getGui().createMessageFromServer(ConnectionMessages.GAME_IS_STARTING_GUI.getMessage());
                }

                client.setClientState(ClientStates.CREATEMODEL);
            }

            case YOUR_TURN, DISCARDDEVCARD, BLACKCROSS1, BLACKCROSS2, BLACKCROSSUPDATE -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)){
                    Constants.printConnectionMessage(message);
                }
                else{
                    client.getGui().createMessageFromServer(ConnectionMessages.YOUR_TURN_GUI.getMessage());
                }

                client.setClientState(ClientStates.GAMESTARTED);
            }

            default ->  throw new IllegalStateException("Unexpected value: " + message);

        }
    }

    public ConnectionMessages getMessage() {
        return message;
    }


}
