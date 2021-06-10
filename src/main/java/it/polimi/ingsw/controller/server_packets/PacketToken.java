package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.singleplayer.SoloActionToken;

public class PacketToken implements ServerPacketHandler{
    private final SoloActionToken soloActionToken;

    @JsonCreator
    public PacketToken(@JsonProperty("soloActionToken") SoloActionToken soloActionToken) {
        this.soloActionToken = soloActionToken;
    }

    public SoloActionToken getSoloActionToken() {
        return soloActionToken;
    }

    @Override
    public void execute(Client client) {
        client.getClientModelView().setSoloActionToken(soloActionToken);

    }
}
