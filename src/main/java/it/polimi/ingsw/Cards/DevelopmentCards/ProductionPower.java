package it.polimi.ingsw.Cards.DevelopmentCards;

import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Exceptions.DepositDoesntHaveThisResource;
import it.polimi.ingsw.Exceptions.DifferentDimensionForProdPower;
import it.polimi.ingsw.Exceptions.EmptyDeposit;
import it.polimi.ingsw.Exceptions.TooManyResourcesRequested;
import it.polimi.ingsw.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * This class represents the Production Power of a Development Card and of the Board.
 */
public class ProductionPower {
    private final HashMap<ResourceType,Integer> resourcesNeeded;
    private final HashMap<ResourceType,Integer> resourcesObtained;

    /**
     * Constructor ProductionPower creates a new ProductionPower instance.
     */
    public ProductionPower(HashMap<ResourceType, Integer> resourcesNeeded, HashMap<ResourceType, Integer> resourceObtained) {
        this.resourcesNeeded = resourcesNeeded;
        this.resourcesObtained= resourceObtained;
    }

    /**
     * Method getResourcesNeeded returns the resources needed to activate the production power.
     */
    public HashMap<ResourceType, Integer> getResourcesNeeded() {
        return resourcesNeeded;
    }

    /**
     * Method getResourceObtained returns the resources obtained from the activation of the production power.
     */
    public HashMap<ResourceType, Integer> getResourcesObtained() {
        return resourcesObtained;
    }

    public boolean check(ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, Board board) throws TooManyResourcesRequested {
        int jollycounter = 0;
        HashMap<ResourceType,Integer> counter = new HashMap<>();
        counter.put(ResourceType.COIN,Collections.frequency(resources,ResourceType.COIN));
        counter.put(ResourceType.STONE,Collections.frequency(resources,ResourceType.STONE));
        counter.put(ResourceType.SERVANT,Collections.frequency(resources,ResourceType.SERVANT));
        counter.put(ResourceType.SHIELD,Collections.frequency(resources,ResourceType.SHIELD));
        if(counter.get(ResourceType.COIN) <= board.getTotalCoins() && counter.get(ResourceType.STONE) <= board.getTotalStones() && counter.get(ResourceType.SERVANT) <= board.getTotalServants() && counter.get(ResourceType.SHIELD) <= board.getTotalShields()){
            for (ResourceType key : counter.keySet()) {
                counter.replace(key,counter.get(key)-resourcesNeeded.get(key));
                if(counter.get(key)<0){
                    jollycounter += (-counter.get(key));
                }
            }
            if(resourcesNeeded.containsKey(ResourceType.JOLLY)){
                jollycounter -= resourcesNeeded.get(ResourceType.JOLLY);
            }
            return counter.get(ResourceType.COIN) >= 0 && counter.get(ResourceType.STONE) >= 0 && counter.get(ResourceType.SERVANT) >= 0 && counter.get(ResourceType.SHIELD) >= 0;
        }
        else{
            throw new TooManyResourcesRequested();
        }
    }
    public void removeResources(ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse) throws DifferentDimensionForProdPower {
        if(resources.size() != warehouse.size()){
            throw new DifferentDimensionForProdPower();
        }
        else {
            for(int i = 0; i<resources.size(); i++){
                try {
                    warehouse.get(i).remove(resources.get(i));
                } catch (DepositDoesntHaveThisResource | EmptyDeposit depositDoesntHaveThisResource) {
                    depositDoesntHaveThisResource.printStackTrace();
                }
            }
        }
    }

    public void addResources(ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, Board board) throws DifferentDimensionForProdPower{
        if(resources.size() != warehouse.size()){
            throw new DifferentDimensionForProdPower();
        }
        else {
            HashMap<ResourceType,Integer> counter = new HashMap<>();
            counter.put(ResourceType.COIN,Collections.frequency(resources,ResourceType.COIN));
            counter.put(ResourceType.STONE,Collections.frequency(resources,ResourceType.STONE));
            counter.put(ResourceType.SERVANT,Collections.frequency(resources,ResourceType.SERVANT));
            counter.put(ResourceType.SHIELD,Collections.frequency(resources,ResourceType.SHIELD));
            for(ResourceType key : resourcesObtained.keySet()){
                if(key == ResourceType.FAITHMARKER){
                    for (int i = 0; i< resourcesObtained.get(key); i++){
                        board.increaseFaithMarker();
                    }
                }
                else {
                    board.getStrongbox().getStrongbox().replace(key,board.getStrongbox().getStrongbox().get(key) + resourcesObtained.get(key));
                }
            }
        }
    }

}
