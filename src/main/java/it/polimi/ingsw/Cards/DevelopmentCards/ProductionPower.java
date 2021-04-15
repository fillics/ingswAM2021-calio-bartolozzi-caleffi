package it.polimi.ingsw.Cards.DevelopmentCards;

import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Board.Storage.Warehouse;
import it.polimi.ingsw.Exceptions.DifferentDimension;
import it.polimi.ingsw.Exceptions.TooManyResourcesRequested;

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
        if(!resourcesNeeded.containsKey(ResourceType.COIN)){
            resourcesNeeded.put(ResourceType.COIN,0);
        }
        if(!resourcesNeeded.containsKey(ResourceType.STONE)){
            resourcesNeeded.put(ResourceType.STONE,0);

        }
        if(!resourcesNeeded.containsKey(ResourceType.SERVANT)){
            resourcesNeeded.put(ResourceType.SERVANT,0);

        }
        if(!resourcesNeeded.containsKey(ResourceType.SHIELD)){
            resourcesNeeded.put(ResourceType.SHIELD,0);
        }
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
                if(counter.get(key)>0){
                    jollycounter += counter.get(key);
                }
            }
            if(resourcesNeeded.containsKey(ResourceType.JOLLY)){
                jollycounter -= resourcesNeeded.get(ResourceType.JOLLY);
            }
            return counter.get(ResourceType.COIN) >= 0 && counter.get(ResourceType.STONE) >= 0 && counter.get(ResourceType.SERVANT) >= 0 && counter.get(ResourceType.SHIELD) >= 0 && jollycounter == 0;
        }
        else{
            throw new TooManyResourcesRequested();
        }
    }

    public void addResources(Board board) {
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
    public void addResources(Board board, ArrayList<ResourceType> newResources)throws DifferentDimension {
        if(newResources.size() != resourcesObtained.get(ResourceType.JOLLY)){
            throw new DifferentDimension();
        }
        else{
            for(ResourceType key : resourcesObtained.keySet()){
                if(key == ResourceType.FAITHMARKER){
                    for (int i = 0; i< resourcesObtained.get(key); i++){
                        board.increaseFaithMarker();
                    }
                }
                else {
                    if(key != ResourceType.JOLLY){
                        board.getStrongbox().getStrongbox().replace(key,board.getStrongbox().getStrongbox().get(key) + resourcesObtained.get(key));
                    }
                    else {
                        for(ResourceType resourceType : newResources){
                            board.getStrongbox().getStrongbox().replace(resourceType, board.getStrongbox().getStrongbox().get(resourceType) + 1);
                        }
                    }
                }
            }
        }
    }

}
