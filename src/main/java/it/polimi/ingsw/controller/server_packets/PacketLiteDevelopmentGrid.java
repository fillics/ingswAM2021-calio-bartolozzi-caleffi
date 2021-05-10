package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.LiteDevelopmentGrid;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;

public class PacketLiteDevelopmentGrid implements ServerPacketHandler{

    private DevelopmentCard[][] developmentCards;

    @JsonCreator
    public PacketLiteDevelopmentGrid(@JsonProperty("development grid :")DevelopmentCard[][] developmentCards) {
        this.developmentCards = developmentCards;
    }
}
