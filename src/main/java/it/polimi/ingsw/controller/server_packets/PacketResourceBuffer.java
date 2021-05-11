package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.model.board.resources.Resource;

import java.util.ArrayList;

public class PacketResourceBuffer {

    private ArrayList<Resource> resourceBuffer;

    @JsonCreator
    public PacketResourceBuffer(@JsonProperty("resource buffer :") ArrayList<Resource> resourceBuffer) {
        this.resourceBuffer = resourceBuffer;
    }
}
