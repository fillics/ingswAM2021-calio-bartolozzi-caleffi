package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;

import javax.swing.*;

public class PacketWinner implements ServerPacketHandler {
    String username;

    @JsonCreator
    public PacketWinner(@JsonProperty("username") String username) {
        this.username = username;
    }

    @Override
    public void execute(Client client) {
        if(client.getViewChoice().equals(ViewChoice.CLI)) System.out.println("The winner of this game is "+ username);
        else JOptionPane.showMessageDialog(client.getGui().getjFrame(), "The winner of this game is" + username);
    }

    public String getUsername() {
        return username;
    }
}
