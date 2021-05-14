package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;

import java.util.ArrayList;

public class PacketLiteDevelopmentGrid implements ServerPacketHandler{

    private final ArrayList<DevelopmentCard> developmentCards;

    @JsonCreator
    public PacketLiteDevelopmentGrid(@JsonProperty("development grid :")ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }

    @Override
    public void execute(Client client) {
        client.getClientModelView().getDevelopmentGrid().setDevelopmentCards(developmentCards);
    }

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }
}
