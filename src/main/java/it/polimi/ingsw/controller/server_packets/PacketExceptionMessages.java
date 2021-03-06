package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.controller.messages.ExceptionMessages;

import javax.swing.*;

/**
 * PacketExceptionMessages used to show the exception messages sent by the model
 */
public class PacketExceptionMessages implements ServerPacketHandler{
    ExceptionMessages message;

    /**
     * Class' constructor.
     * @param message is the exception message to send to the client.
     */
    @JsonCreator
    public PacketExceptionMessages(@JsonProperty("message")ExceptionMessages message) {
        this.message = message;
    }

    /**
     * Method execute() sends a message to the client.
     */
    @Override
    public void execute(Client client) {
        if(client.getViewChoice() == ViewChoice.CLI) Constants.printExceptionMessage(message);
        else{
            JOptionPane.showMessageDialog(client.getGui().getjFrame(), message.getMessage());
        }
    }

    public ExceptionMessages getMessage() {
        return message;
    }

}
