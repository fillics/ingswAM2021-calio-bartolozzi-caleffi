package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.GUI;
import it.polimi.ingsw.client.gui.panels.WinnerPanel;
import it.polimi.ingsw.client.gui.panels.pregamepanels.ServerPanel;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.PlayerInfoEndMatch;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class PacketWinner implements ServerPacketHandler {
    private final String username;
    private final ArrayList<PlayerInfoEndMatch> players;

    @JsonCreator
    public PacketWinner(@JsonProperty("username") String username, 
                        @JsonProperty("players") ArrayList<PlayerInfoEndMatch> players) {
        this.username = username;
        this.players = players;
    }

    

    @Override
    public void execute(Client client) {
        if(client.getViewChoice().equals(ViewChoice.CLI)) {
            Constants.winner(username);
            System.out.println("LeaderBoard: ");
            for (PlayerInfoEndMatch player: players){
                System.out.println(player.getUsername().toUpperCase(Locale.ROOT) + ": "+player.getTotVictory()+" victory points");
            }
            System.out.println(Constants.close);
        }
        else {
            JOptionPane.showMessageDialog(client.getGui().getjFrame(), "The game is ended. Click OK to see the winner!");
            client.getGui().createMessageFromServer("GAME ENDED!");
            if(players != null) client.getGui().switchPanels(new WinnerPanel(client.getGui(), players, username));
            else client.getGui().switchPanels(new WinnerPanel(client.getGui(), null, username));

        }
        client.setClientState(ClientStates.END);

    }

    public String getUsername() {
        return username;
    }

    public ArrayList<PlayerInfoEndMatch> getPlayers() {
        return players;
    }


}
