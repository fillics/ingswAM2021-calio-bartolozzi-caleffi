package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;

import java.util.ArrayList;

public class PacketDevelopmentSpaces implements ServerPacketHandler{

    private final ArrayList<DevelopmentSpace> developmentSpaces;

    @JsonCreator
    public PacketDevelopmentSpaces(@JsonProperty("development spaces :")ArrayList<DevelopmentSpace> developmentSpaces) {
        this.developmentSpaces = developmentSpaces;
    }

    @Override
    public void execute(Client client) {
        client.getClientModelView().getLiteBoard().setDevelopmentSpaces(developmentSpaces);
        System.out.println("Choose one of the operations you can do:\nPress 0 to quit");
        System.out.println("1: Activate a Leader Card\n" +
                "2: Buy a Development Card\n" +
                "3: Choose Discount\n" +
                "4: Use production powers\n" +
                "5: Discard a Leader Card\n" +
                "6: Move one of you resources\n" +
                "7: Place one of your resources\n" +
                "8: Take resources from the market\n" +
                "9: End Turn\n");
    }

    public ArrayList<DevelopmentSpace> getDevelopmentSpaces() {
        return developmentSpaces;
    }
}
