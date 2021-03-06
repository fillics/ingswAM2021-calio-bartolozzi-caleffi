package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.client.gui.panels.pregamepanels.AdditionalResourcePanel;
import it.polimi.ingsw.client.gui.panels.BoardPanel;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.util.ArrayList;

/**
 * PacketLeaderCards represents the Lite Leader Cards class, for the lite model of the client
 */
public class PacketLeaderCards implements ServerPacketHandler{

    private final ArrayList<LeaderCard> leaderCards;

    /**
     * Class' constructor.
     * @param leaderCards represents the leader cards.
     */
    @JsonCreator
    public PacketLeaderCards(@JsonProperty("leader cards :") ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }

    public ArrayList<LeaderCard> getLeaderCards() {
        return leaderCards;
    }

    /**
     * Method execute() updates the leader cards value in LitePlayer class.
     */
    @Override
    public void execute(Client client) {
        client.getClientModelView().getMyPlayer().setLeaderCards(leaderCards);

        switch (client.getClientState()){
            case GAMESTARTED -> {
                if(client.getViewChoice().equals(ViewChoice.GUI)){
                    client.getGui().switchPanels(new BoardPanel(client.getGui()));
                }
                else System.out.println("[from server]"+Constants.ANSI_GREEN+" Leader Cards updated!"+Constants.ANSI_RESET);
            }
            case LEADERSETUP -> {
                if (client.getClientModelView().getMyPlayer().getPosInGame() != 0) {
                    if(client.getViewChoice().equals(ViewChoice.GUI)){
                        client.getGui().switchPanels(new AdditionalResourcePanel(client.getGui()));
                    }
                    else System.out.println("Choose your action:\n1. Choose your optional resources");
                    client.setClientState(ClientStates.RESOURCESETUP);
                }
                else {
                    if(client.getViewChoice().equals(ViewChoice.GUI)){
                        client.getGui().switchPanels(new BoardPanel(client.getGui()));
                        client.getGui().createMessageFromServer("You're the first player, you can't have any resources or faith points.\nGood game!");
                    }
                    else {
                        System.out.println("[from server]"+Constants.ANSI_GREEN+" Leader Cards updated!"+Constants.ANSI_RESET);
                        System.out.println(Constants.ANSI_YELLOW+"You're the first player, you can't have any resources or faith points."+Constants.ANSI_RESET);
                        System.out.println(Constants.menu);
                    }

                    client.setClientState(ClientStates.GAMESTARTED);
                }
            }
        }

    }
}
