package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.client.gui.panels.WaitingEndGamePanel;
import it.polimi.ingsw.client.gui.panels.pregamepanels.NumPlayersPanel;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ConnectionMessages;

import javax.swing.*;

/**
 * PacketConnectionMessages handles the server messages that send to the client
 */
public class PacketConnectionMessages implements ServerPacketHandler {

    private ConnectionMessages message;

    /**
     * Class' constructor.
     * @param message is the connection message to send to the client.
     */
    @JsonCreator
    public PacketConnectionMessages(@JsonProperty("message") ConnectionMessages message) {
        this.message = message;
    }

    /**
     * Method execute() sends a message to the client.
     */
    @Override
    public void execute(Client client) {
        switch (message){

            case USERNAME_VALID -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)) Constants.printConnectionMessage(message);
            }
            case LAST_TURN -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)) Constants.printConnectionMessage(message);

                else{
                    client.getGui().createMessageFromServer(message.getMessage());
                    client.getGui().switchPanels(new WaitingEndGamePanel(client.getGui()));
                }
                client.setClientState(ClientStates.GAME_ENDING);

            }

            case IMPOSSIBLEENDTURN, IMPOSSIBLEMOVE, IMPOSSIBLE_CHEAT, NOT_YOUR_TURN -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)) Constants.printConnectionMessage(message);

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
                    client.getGui().switchPanels(new NumPlayersPanel(client.getGui()));
                }
                client.setClientState(ClientStates.NUMPLAYERS);

            }

            case TAKEN_NICKNAME -> {

                if(client.getViewChoice().equals(ViewChoice.CLI)) Constants.printConnectionMessage(message);

                else {
                    JOptionPane.showMessageDialog(client.getGui().getjFrame(), ConnectionMessages.TAKEN_NICKNAME_GUI.getMessage());
                }

                client.setClientState(ClientStates.USERNAME);
            }

            case WAITING_PEOPLE -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)) Constants.printConnectionMessage(message);

                else{
                    client.getGui().createMessageFromServer(ConnectionMessages.WAITING_PEOPLE.getMessage());
                }

                client.setClientState(ClientStates.WAITPLAYERS);
            }

            case GAME_IS_STARTING -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)) Constants.printConnectionMessage(message);

                else{
                    client.getGui().createMessageFromServer(ConnectionMessages.GAME_IS_STARTING_GUI.getMessage());
                }

                client.setClientState(ClientStates.CREATEMODEL);
            }

            case YOUR_TURN, DISCARDDEVCARD, BLACKCROSS1, BLACKCROSS2, BLACKCROSSUPDATE -> {

                if(client.getViewChoice().equals(ViewChoice.CLI)) Constants.printConnectionMessage(message);

                else{
                    if(message.equals(ConnectionMessages.YOUR_TURN) || message.equals(ConnectionMessages.BLACKCROSSUPDATE))
                        client.getGui().createMessageFromServer(ConnectionMessages.YOUR_TURN_GUI.getMessage());
                    else {
                        switch (message){
                            case DISCARDDEVCARD -> message=ConnectionMessages.DISCARDDEVCARD_GUI;
                            case BLACKCROSS1 -> message=ConnectionMessages.BLACKCROSS1_GUI;
                            case BLACKCROSS2 -> message=ConnectionMessages.BLACKCROSS2_GUI;
                        }
                        JOptionPane.showMessageDialog(client.getGui().getjFrame(), message.getMessage());

                    }
                    client.getGui().switchPanels(new BoardPanel(client.getGui()));
                }
                client.setClientState(ClientStates.GAMESTARTED);
            }

            case UPDATE_AFTER_ENDTURN ->{
                if(client.getViewChoice().equals(ViewChoice.GUI)){
                    client.getGui().switchPanels(new BoardPanel(client.getGui()));
                }
            }

            case WAIT_FOR_TURN -> {
                if(client.getViewChoice().equals(ViewChoice.CLI)) Constants.printConnectionMessage(message);

                else{
                    client.getGui().createMessageFromServer(message.getMessage());
                }
            }
            default ->  throw new IllegalStateException("Unexpected value: " + message);

        }
    }

    public ConnectionMessages getMessage() {
        return message;
    }


}
