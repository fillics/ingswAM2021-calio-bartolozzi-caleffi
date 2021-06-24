package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.panels.WinnerPanel;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerInfoEndMatch;

import javax.swing.*;
import java.util.ArrayList;

public class PacketWinner implements ServerPacketHandler {
    private String username;
    private ArrayList<PlayerInfoEndMatch> players;
    private boolean isSingleGame=false;

    //for multiplayer games
    @JsonCreator
    public PacketWinner(@JsonProperty("username") String username, 
                        @JsonProperty("players") ArrayList<PlayerInfoEndMatch> players) {
        this.username = username;
        this.players = players;
        
    }

    //for single player game
    @JsonCreator
    public PacketWinner(@JsonProperty("username") String username) {
        this.username = username;
        isSingleGame=true;
    }
    

    @Override
    public void execute(Client client) {
        if(client.getViewChoice().equals(ViewChoice.CLI)) System.out.println("The winner of this game is "+ username);
        else {
            JOptionPane.showMessageDialog(client.getGui().getjFrame(), "The winner of this game is " + username);
            if(isSingleGame) client.getGui().switchPanels(new WinnerPanel(client.getGui(), players));
            else client.getGui().switchPanels(new WinnerPanel(client.getGui(), players)); /// TODO: 24/06/2021 fare pannello attesa turno altri giocatori 
        }
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<PlayerInfoEndMatch> getPlayers() {
        return players;
    }
}
