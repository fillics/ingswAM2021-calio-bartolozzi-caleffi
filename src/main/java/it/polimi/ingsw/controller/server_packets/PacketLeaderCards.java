package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.cards.leadercards.LeaderCard;

import java.util.ArrayList;

public class PacketLeaderCards implements ServerPacketHandler{

    private ArrayList<LeaderCard> leaderCards;

    @JsonCreator
    public PacketLeaderCards(@JsonProperty("leader cards :") ArrayList<LeaderCard> leaderCards) {
        this.leaderCards = leaderCards;
    }
}
