package it.polimi.ingsw.controller.packets;

import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;

import java.util.ArrayList;

public class PacketUseAndChooseProdPower implements PacketHandler {
    private ProductionPower productionPower;
    private ArrayList<ResourceType> resourceTypes;
    private ArrayList<Warehouse> warehouse;
    private ArrayList<ResourceType> newResources;

    public PacketUseAndChooseProdPower(ProductionPower productionPower, ArrayList<ResourceType> resourceTypes, ArrayList<Warehouse> warehouse, ArrayList<ResourceType> newResources) {
        this.productionPower = productionPower;
        this.resourceTypes = resourceTypes;
        this.warehouse = warehouse;
        this.newResources = newResources;
    }

    @Override
    public void execute(GameInterface gameInterface) {

    }
}
