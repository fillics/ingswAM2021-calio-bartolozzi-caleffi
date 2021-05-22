package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.constants.Constants;
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
        System.out.println("[from server]"+ Constants.ANSI_GREEN+" Development space updated!"+Constants.ANSI_RESET);
    }

    public ArrayList<DevelopmentSpace> getDevelopmentSpaces() {
        return developmentSpaces;
    }
}
