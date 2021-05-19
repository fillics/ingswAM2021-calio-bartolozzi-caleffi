package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientStates;
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
        if(client.getClientState().equals(ClientStates.LEADERSETUP)) {
            client.getClientModelView().getMyPlayer().setLeaderCards(leaderCards);
            System.out.println("Leader cards updated");
            if (client.getClientModelView().getMyPlayer().getPosInGame() != 0) {
                client.setClientState(ClientStates.RESOURCESETUP);
                System.out.println("Choose your action:\n1. Choose your optional resources");
            } else {
                client.setClientState(ClientStates.GAMESTARTED);
                System.out.println("You're the first player, you can't have any resources or faith points");
                System.out.println(Constants.menu);
            }
        }
        if(client.getClientState().equals(ClientStates.GAMESTARTED)) {
            client.getClientModelView().getMyPlayer().setLeaderCards(leaderCards);
        }
    }
}
