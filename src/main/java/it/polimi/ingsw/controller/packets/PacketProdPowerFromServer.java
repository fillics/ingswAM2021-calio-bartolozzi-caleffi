package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

import java.io.Serializable;
import java.util.ArrayList;

public class PacketProdPowerFromServer implements Serializable {

    private String to;
    private ArrayList<ProductionPower> availableProdPowers;
}
