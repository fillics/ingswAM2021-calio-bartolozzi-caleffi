package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.board.resources.Resource;

import java.util.ArrayList;

public class PacketResourceBuffer implements ServerPacketHandler{

    private final ArrayList<Resource> resourceBuffer;

    @JsonCreator
    public PacketResourceBuffer(@JsonProperty("resourceBuffer") ArrayList<Resource> resourceBuffer) {
        this.resourceBuffer = resourceBuffer;
    }

    @Override
    public void execute(Client client) {
        System.out.println("Resource buffer updated");
        client.getClientModelView().getMyPlayer().setResourceBuffer(resourceBuffer);
    }

    public ArrayList<Resource> getResourceBuffer() {
        return resourceBuffer;
    }
}

