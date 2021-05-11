package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PacketWhiteMarbleChoice implements ServerPacketHandler{
    private ArrayList<Integer> whiteMarbleCardChoice;

    @JsonCreator
    public PacketWhiteMarbleChoice(@JsonProperty("white marble leader card's id :") ArrayList<Integer> whiteMarbleCardChoice) {
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
    }
}
