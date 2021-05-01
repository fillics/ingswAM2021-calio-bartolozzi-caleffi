package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.DevelopmentSpace;

import java.util.ArrayList;


public class PacketBuyDevCard implements HandlePacket {
    private int id;
    private ArrayList<ResourceType> chosenResources;
    private ArrayList<Warehouse> chosenWarehouses;
    private DevelopmentSpace developmentSpace;

    public PacketBuyDevCard(int id, ArrayList<ResourceType> chosenResources, ArrayList<Warehouse> chosenWarehouses, DevelopmentSpace developmentSpace) {
        this.id = id;
        this.chosenResources = chosenResources;
        this.chosenWarehouses = chosenWarehouses;
        this.developmentSpace = developmentSpace;
    }

    @Override
    public void execute(GameInterface gameInterface) {

    }
}
