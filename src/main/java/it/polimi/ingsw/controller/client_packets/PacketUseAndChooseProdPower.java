package it.polimi.ingsw.controller.client_packets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.controller.messages.ConnectionMessages;
import it.polimi.ingsw.controller.messages.ExceptionMessages;
import it.polimi.ingsw.controller.GameStates;
import it.polimi.ingsw.controller.server_packets.PacketConnectionMessages;
import it.polimi.ingsw.controller.server_packets.PacketExceptionMessages;
import it.polimi.ingsw.controller.server_packets.PacketFaithTrack;
import it.polimi.ingsw.controller.server_packets.PacketWarehouse;
import it.polimi.ingsw.exceptions.*;
import it.polimi.ingsw.model.gameinterfaces.GameInterface;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.model.cards.developmentcards.ProductionPower;
import it.polimi.ingsw.server.ClientHandler;
import it.polimi.ingsw.server.Server;

import java.util.ArrayList;
import java.util.HashMap;

public class PacketUseAndChooseProdPower implements ClientPacketHandler {
    private final ArrayList<Integer> productionPowers;
    private final ArrayList<Integer> newProductionPowers;
    private final ArrayList<ResourceType> resourceTypes;
    private final ArrayList<Integer> warehouse;
    private final ArrayList<ResourceType> newResources;

    @JsonCreator
    public PacketUseAndChooseProdPower(@JsonProperty("ProductionPowers")ArrayList<Integer> productionPowers,@JsonProperty("new" +
            "ProductionPowers")ArrayList<Integer> newProductionPowers,@JsonProperty("ResourceTypes") ArrayList<ResourceType> resourceTypes,@JsonProperty("Warehouse") ArrayList<Integer> warehouse,@JsonProperty("NewResources") ArrayList<ResourceType> newResources) {
        this.productionPowers = productionPowers;
        this.resourceTypes = resourceTypes;
        this.warehouse = warehouse;
        this.newResources = newResources;
        this.newProductionPowers = newProductionPowers;
    }

    @Override
    public void execute(Server server, GameInterface gameInterface, ClientHandler clientHandler) {
        //TODO: GUARDARE METTENDO UNA CARTA LEADER ATTIVATA NEL MODELLO COME MAI NON FUNZIONA IL PROD POWER
        if(gameInterface.getState().equals(GameStates.PHASE_ONE) && clientHandler.getPosInGame() == gameInterface.getCurrentPlayer()){
            ArrayList<ProductionPower> realProductionPowers = new ArrayList<>();
            for(int i: productionPowers){
                realProductionPowers.add(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDevelopmentSpaces().get(i - 1).getTopCardProductionPower());
            }
            for(int j: newProductionPowers){
                realProductionPowers.add(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getSpecialProductionPowers().get(j -1));
            }

            ArrayList<Warehouse> realChosenWarehouses = new ArrayList<>();
            for(int k : warehouse){
                switch (k) {
                    case 1 -> realChosenWarehouses.add(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits().get(0));
                    case 2 -> realChosenWarehouses.add(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits().get(1));
                    case 3 -> realChosenWarehouses.add(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits().get(2));
                    case 4 ->realChosenWarehouses.add(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getStrongbox());
                }
            }

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


            for (ProductionPower productionPower : realProductionPowers) {
                for (ResourceType key : productionPower.getResourceNeeded().keySet()) {
                    resourceNeeded.replace(key, resourceNeeded.get(key) + productionPower.getResourceNeeded().get(key));
                }
                for (ResourceType key : productionPower.getResourceObtained().keySet()) {
                    resourceObtained.replace(key, resourceObtained.get(key) + productionPower.getResourceObtained().get(key));
                }
            }
            ProductionPower newProductionPower = new ProductionPower(resourceNeeded, resourceObtained);
            try {
                gameInterface.useAndChooseProdPower(newProductionPower, resourceTypes, realChosenWarehouses, newResources);
                clientHandler.sendPacketToClient(new PacketWarehouse(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getStrongbox(),
                        gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits()));
                clientHandler.sendPacketToClient(new PacketFaithTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack(), gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker(),gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getVaticanReportSections()));
                gameInterface.setState(GameStates.PHASE_TWO);
            } catch (DifferentDimension differentDimension) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DIFFERENTDIMENSION));
            } catch (TooManyResourcesRequested tooManyResourcesRequested) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.TOOMANYRESOURCESREQUESTED));
            } catch (EmptyDeposit emptyDeposit) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.EMPTYDEPOSIT));
            } catch (DepositDoesntHaveThisResource depositDoesntHaveThisResource) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.DEPOSITDOESNTHAVETHISRESOURCE));
            } catch (NotEnoughChosenResources notEnoughChosenResources) {
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.NOTENOUGHCHOSENRESOURCES));
            } catch (EmptyProductionPower emptyProductionPower){
                clientHandler.sendPacketToClient(new PacketExceptionMessages(ExceptionMessages.EMPTYPRODPOWER));
            }


            //SALVATAGGIO SU PROXY
            server.getMapForReconnection().get(clientHandler.getUsername()).getClientModelView().getLiteBoard().
                    setStrongbox(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getStrongbox());

            server.getMapForReconnection().get(clientHandler.getUsername()).getClientModelView().getLiteBoard().
                    setDeposits(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getDeposits());
            server.getMapForReconnection().get(clientHandler.getUsername()).getClientModelView().getLiteBoard().
                    setTrack(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getTrack());
            server.getMapForReconnection().get(clientHandler.getUsername()).getClientModelView().getLiteBoard().
                    setFaithMarker(gameInterface.getActivePlayers().get(gameInterface.getCurrentPlayer()).getBoard().getFaithMarker());
        }
        else {
            clientHandler.sendPacketToClient(new PacketConnectionMessages(ConnectionMessages.IMPOSSIBLEMOVE));
        }
    }

    public ArrayList<Integer> getProductionPowers() {
        return productionPowers;
    }

    public ArrayList<ResourceType> getResourceTypes() {
        return resourceTypes;
    }

    public ArrayList<Integer> getWarehouse() {
        return warehouse;
    }

    public ArrayList<ResourceType> getNewResources() {
        return newResources;
    }

    public ArrayList<Integer> getNewProductionPowers() {
        return newProductionPowers;
    }
}
