package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.constants.Constants;
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
        System.out.println("[from server]"+ Constants.ANSI_GREEN+" Resource Buffer updated!"+Constants.ANSI_RESET);
        client.getClientModelView().getMyPlayer().setResourceBuffer(resourceBuffer);
        System.out.println("Remember to place the resources typing the number 7 (Place one of your resources)");
    }

    public ArrayList<Resource> getResourceBuffer() {
        return resourceBuffer;
    }
}

