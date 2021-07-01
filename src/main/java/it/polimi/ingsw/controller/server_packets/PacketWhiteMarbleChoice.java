package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;

import java.util.ArrayList;

/**
 * PacketWhiteMarbleChoice set the white marble choice of the player when a White Marble Leader Card is activated
 */
public class PacketWhiteMarbleChoice implements ServerPacketHandler{
    private final ArrayList<Integer> whiteMarbleCardChoice;

    /**
     * Class' constructor.
     * @param whiteMarbleCardChoice represents the id of the white marble leader card.
     */
    @JsonCreator
    public PacketWhiteMarbleChoice(@JsonProperty("white marble leader card's id :") ArrayList<Integer> whiteMarbleCardChoice) {
        this.whiteMarbleCardChoice = whiteMarbleCardChoice;
    }

    public ArrayList<Integer> getWhiteMarbleCardChoice() {
        return whiteMarbleCardChoice;
    }

    /**
     * Method execute() updates the white Marble Card Choice value in LitePlayer class.
     */
    @Override
    public void execute(Client client) {
        client.getClientModelView().getMyPlayer().setWhiteMarbleCardChoice(whiteMarbleCardChoice);
    }
}
