package it.polimi.ingsw.controller.server_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.ClientModelView;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

import java.util.ArrayList;

public class PacketSpecialProdPowers implements ServerPacketHandler{

    private ArrayList<ProductionPower> specialProductionPowers;

    @JsonCreator
    public PacketSpecialProdPowers(@JsonProperty("special production powers :")ArrayList<ProductionPower> specialProductionPowers) {
        this.specialProductionPowers = specialProductionPowers;
    }

    public ArrayList<ProductionPower> getSpecialProductionPowers() {
        return specialProductionPowers;
    }

    @Override
    public void execute(ClientModelView clientModelView) {
        clientModelView.getLiteBoard().setSpecialProductionPowers(specialProductionPowers);
    }
}
