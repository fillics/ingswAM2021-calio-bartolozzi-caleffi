package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.model.board.resources.Resource;

import java.util.ArrayList;

public class PacketResourceBuffer implements ServerPacketHandler{

    private final ArrayList<Resource> resourceBuffer;

    @JsonCreator
    public PacketResourceBuffer(@JsonProperty("resource buffer :") ArrayList<Resource> resourceBuffer) {
        this.resourceBuffer = resourceBuffer;
    }


    @Override
    public void execute(ClientModelView clientModelView) {
        clientModelView.getMyPlayer().setResourceBuffer(resourceBuffer);
    }
}
