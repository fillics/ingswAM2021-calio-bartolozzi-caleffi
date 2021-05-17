package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.board.resources.Resource;

import java.util.ArrayList;

public class PacketResourceBuffer implements ServerPacketHandler{

    private final ArrayList<Resource> resourceBuffer;

    @JsonCreator
    public PacketResourceBuffer(@JsonProperty("resource buffer :") ArrayList<Resource> resourceBuffer) {
        this.resourceBuffer = resourceBuffer;
    }

    public ArrayList<Resource> getResourceBuffer() {
        return resourceBuffer;
    }

    @Override
    public void execute(Client client) {
        client.getClientModelView().getMyPlayer().setResourceBuffer(resourceBuffer);
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
}
