package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Client;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

import java.util.ArrayList;

public class PacketSpecialProdPowers implements ServerPacketHandler{

    private final ArrayList<ProductionPower> specialProductionPowers;

    /**
     * Class' constructor.
     * @param specialProductionPowers represents the special producion powers.
     */
    @JsonCreator
    public PacketSpecialProdPowers(@JsonProperty("special production powers :")ArrayList<ProductionPower> specialProductionPowers) {
        this.specialProductionPowers = specialProductionPowers;
    }

    public ArrayList<ProductionPower> getSpecialProductionPowers() {
        return specialProductionPowers;
    }

    /**
     * Method execute() updates the special production powers value in LiteBoard class.
     */
    @Override
    public void execute(Client client) {
        client.getClientModelView().getLiteBoard().setSpecialProductionPowers(specialProductionPowers);
    }
}
