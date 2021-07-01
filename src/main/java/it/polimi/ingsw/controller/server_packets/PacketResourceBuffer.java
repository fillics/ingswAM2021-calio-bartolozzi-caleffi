package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.model.board.resources.Resource;

import java.util.ArrayList;

public class PacketResourceBuffer implements ServerPacketHandler{

    private final ArrayList<Resource> resourceBuffer;

    /**
     * Class' constructor.
     * @param resourceBuffer is the value of the resource buffer.
     */
    @JsonCreator
    public PacketResourceBuffer(@JsonProperty("resourceBuffer") ArrayList<Resource> resourceBuffer) {
        this.resourceBuffer = resourceBuffer;
    }

    /**
     * Method execute() updates the resource buffer value in LitePlayer class.
     */
    @Override
    public void execute(Client client) {
        client.getClientModelView().getMyPlayer().setResourceBuffer(resourceBuffer);
        if(client.getViewChoice().equals(ViewChoice.CLI)){
            System.out.println("Remember to place the resources typing the number 7 (Place one of your resources)");
        }
    }

    public ArrayList<Resource> getResourceBuffer() {
        return resourceBuffer;
    }
}

