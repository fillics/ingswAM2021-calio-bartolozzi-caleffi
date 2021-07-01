package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.client.ViewChoice;
import it.polimi.ingsw.constants.Constants;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentCard;

import java.util.ArrayList;

/**
 * PacketLiteDevelopmentGrid represents the Lite Development Grid class, for the lite model of the client
 */
public class PacketLiteDevelopmentGrid implements ServerPacketHandler{

    private final ArrayList<DevelopmentCard> developmentCards;

    /**
     * Class' constructor.
     * @param developmentCards represents the top development cards in the development grid.
     */
    @JsonCreator
    public PacketLiteDevelopmentGrid(@JsonProperty("development grid :")ArrayList<DevelopmentCard> developmentCards) {
        this.developmentCards = developmentCards;
    }

    /**
     * Method execute() updates the development cards value in LiteDevelopmentGrid class.
     */
    @Override
    public void execute(Client client) {
        client.getClientModelView().getDevelopmentGrid().setDevelopmentCards(developmentCards);
        if(client.getViewChoice().equals(ViewChoice.CLI)){
            System.out.println("[from server]"+Constants.ANSI_GREEN+" Development grid updated!"+Constants.ANSI_RESET);
        }

    }

    public ArrayList<DevelopmentCard> getDevelopmentCards() {
        return developmentCards;
    }
}
