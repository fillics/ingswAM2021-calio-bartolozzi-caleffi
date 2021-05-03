package it.polimi.ingsw.controller.client_packets;

import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.exceptions.NumMaxPlayersReached;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.server.SocketConnection;

import java.util.ArrayList;

public class PacketUseAndChooseProdPower implements PacketHandler {
    private ArrayList<ProductionPower> productionPowers;
    private ArrayList<ResourceType> resourceTypes;
    private ArrayList<Warehouse> warehouse;
    private ArrayList<ResourceType> newResources;

    public PacketUseAndChooseProdPower(ArrayList<ProductionPower> productionPowers, ArrayList<ResourceType> resourceTypes, ArrayList<Warehouse> warehouse, ArrayList<ResourceType> newResources) {
        this.productionPowers = productionPowers;
        this.resourceTypes = resourceTypes;
        this.warehouse = warehouse;
        this.newResources = newResources;
    }

    @Override
    public void execute(GameInterface gameInterface, SocketConnection socketConnection) throws NumMaxPlayersReached {

    }
}
