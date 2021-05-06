package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.LeaderCardNotFound;
import it.polimi.ingsw.exceptions.NotEnoughRequirements;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.client.SocketClientConnected;

import java.net.Socket;

public class PacketActivateLeaderCard implements PacketHandler {
    private int ID;

    @JsonCreator
    public PacketActivateLeaderCard(@JsonProperty("ID: ")int ID) {
        this.ID = ID;
    }



    @Override
    public void execute(GameInterface gameInterface, Socket socketClientConnected) throws LeaderCardNotFound, NotEnoughRequirements {
        gameInterface.activateLeaderCard(ID);
    }
}
