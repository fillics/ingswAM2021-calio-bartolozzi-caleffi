package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.PacketHandler;
import it.polimi.ingsw.controller.State;
import it.polimi.ingsw.exceptions.DepositDoesntHaveThisResource;
import it.polimi.ingsw.exceptions.DifferentDimension;
import it.polimi.ingsw.exceptions.EmptyDeposit;
import it.polimi.ingsw.exceptions.TooManyResourcesRequested;
import it.polimi.ingsw.model.GameInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;
import java.util.HashMap;

public class PacketUseAndChooseProdPower implements PacketHandler {
    private ArrayList<ProductionPower> productionPowers;
    private ArrayList<ResourceType> resourceTypes;
    private ArrayList<Warehouse> warehouse;
    private ArrayList<ResourceType> newResources;

    @JsonCreator
    public PacketUseAndChooseProdPower(@JsonProperty("production powers: ")ArrayList<ProductionPower> productionPowers,@JsonProperty("resource types: ") ArrayList<ResourceType> resourceTypes,@JsonProperty("warehouse: ") ArrayList<Warehouse> warehouse,@JsonProperty("new resources: ") ArrayList<ResourceType> newResources) {
        this.productionPowers = productionPowers;
        this.resourceTypes = resourceTypes;
        this.warehouse = warehouse;
        this.newResources = newResources;
    }

    //TODO: Decidere se aggiungere l'else all'if che invia il messaggio al client che gli dà errore
    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) throws EmptyDeposit, DepositDoesntHaveThisResource, TooManyResourcesRequested, DifferentDimension {
        if(gameInterface.getState() == State.PHASE_ONE && clientHandler.getIdClient() == gameInterface.getCurrentPlayer()){
            HashMap<ResourceType, Integer> resourceNeeded = new HashMap<>();
            resourceNeeded.put(ResourceType.COIN, 0);
            resourceNeeded.put(ResourceType.STONE, 0);
            resourceNeeded.put(ResourceType.SERVANT, 0);
            resourceNeeded.put(ResourceType.SHIELD, 0);
            resourceNeeded.put(ResourceType.JOLLY, 0);

            HashMap<ResourceType, Integer> resourceObtained = new HashMap<>();
            resourceObtained.put(ResourceType.COIN, 0);
            resourceObtained.put(ResourceType.STONE, 0);
            resourceObtained.put(ResourceType.SERVANT, 0);
            resourceObtained.put(ResourceType.SHIELD, 0);
            resourceObtained.put(ResourceType.JOLLY, 0);


            for (ProductionPower productionPower : productionPowers) {
                for (ResourceType key : productionPower.getResourcesNeeded().keySet()) {
                    resourceNeeded.replace(key, resourceNeeded.get(key) + productionPower.getResourcesNeeded().get(key));
                }

                for (ResourceType key : productionPower.getResourcesNeeded().keySet()) {
                    resourceObtained.replace(key, resourceObtained.get(key) + productionPower.getResourcesObtained().get(key));
                }
            }

            ProductionPower newProductionPower = new ProductionPower(resourceNeeded, resourceObtained);
            gameInterface.useAndChooseProdPower(newProductionPower, resourceTypes, warehouse, newResources);

            gameInterface.setState(State.PHASE_TWO);
        }

    }
}
