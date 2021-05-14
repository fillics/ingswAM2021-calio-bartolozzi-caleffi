package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.ClientModelView;

import java.util.ArrayList;

public class PacketWhiteMarbleChoice implements ServerPacketHandler{
    private final ArrayList<Integer> whiteMarbleCardChoice;

    @JsonCreator
    public PacketWhiteMarbleChoice(@JsonProperty("white marble leader card's id :") ArrayList<Integer> whiteMarbleCardChoice) {
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
    }

    public ArrayList<Integer> getWhiteMarbleCardChoice() {
        return whiteMarbleCardChoice;
    }

    @Override
    public void execute(ClientModelView clientModelView) {
        clientModelView.getMyPlayer().setWhiteMarbleCardChoice(whiteMarbleCardChoice);
    }
}
