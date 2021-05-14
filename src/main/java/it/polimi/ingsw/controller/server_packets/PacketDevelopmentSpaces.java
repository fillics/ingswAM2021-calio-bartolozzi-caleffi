package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;

import java.util.ArrayList;

public class PacketDevelopmentSpaces implements ServerPacketHandler{

    private final ArrayList<DevelopmentSpace> developmentSpaces;

    @JsonCreator
    public PacketDevelopmentSpaces(@JsonProperty("development spaces :")ArrayList<DevelopmentSpace> developmentSpaces) {
        this.developmentSpaces = developmentSpaces;
    }

    @Override
    public void execute(ClientModelView clientModelView) {
        clientModelView.getLiteBoard().setDevelopmentSpaces(developmentSpaces);
    }

}
