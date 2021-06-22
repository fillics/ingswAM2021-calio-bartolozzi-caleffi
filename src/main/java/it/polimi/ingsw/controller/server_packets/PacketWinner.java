package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.panels.WinnerPanel;
import it.polimi.ingsw.model.Player;

import javax.swing.*;
import java.util.ArrayList;

public class PacketWinner implements ServerPacketHandler {
    private String username;
    private ArrayList<Player> players;

    @JsonCreator
    public PacketWinner(@JsonProperty("username") String username, @JsonProperty("players") ArrayList<Player> players) {
        this.username = username;
        this.players = players;
    }

    @Override
    public void execute(Client client) {
        if(client.getViewChoice().equals(ViewChoice.CLI)) System.out.println("The winner of this game is "+ username);
        else {
            JOptionPane.showMessageDialog(client.getGui().getjFrame(), "The winner of this game is " + username);
            client.getGui().switchPanels(new WinnerPanel(client.getGui(), players));
        }
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
