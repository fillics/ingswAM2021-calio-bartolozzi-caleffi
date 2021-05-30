package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.client.gui.panels.AdditionalResourcePanel;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.util.ArrayList;

public class PacketLeaderCards implements ServerPacketHandler{

    private final ArrayList<LeaderCard> leaderCards;

    @JsonCreator
    public PacketLeaderCards(@JsonProperty("leader cards :") ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    @Override
    public void execute(Client client) {
        client.getClientModelView().getMyPlayer().setLeaderCards(leaderCards);

        switch (client.getClientState()){
            case GAMESTARTED -> System.out.println("[from server]"+Constants.ANSI_GREEN+" Leader Cards updated!"+Constants.ANSI_RESET);
            case LEADERSETUP -> {
                if (client.getClientModelView().getMyPlayer().getPosInGame() != 0) {
                    if(client.getViewChoice().equals(ViewChoice.GUI)){
                        AdditionalResourcePanel additionalResourcePanel = new AdditionalResourcePanel(client.getGui());
                        client.getGui().switchPanels(additionalResourcePanel);
                    }
                    client.setClientState(ClientStates.RESOURCESETUP);
                    System.out.println("Choose your action:\n1. Choose your optional resources");
                } else {
                    if(client.getViewChoice().equals(ViewChoice.GUI)){
                        BoardPanel boardPanel = new BoardPanel(client.getGui());
                        client.getGui().switchPanels(boardPanel);
                    }
                    client.setClientState(ClientStates.GAMESTARTED);
                    System.out.println("[from server]"+Constants.ANSI_GREEN+" Leader Cards updated!"+Constants.ANSI_RESET);
                    System.out.println(Constants.ANSI_YELLOW+"You're the first player, you can't have any resources or faith points."+Constants.ANSI_RESET);
                    System.out.println(Constants.menu);
                }
            }
        }

    }
}
