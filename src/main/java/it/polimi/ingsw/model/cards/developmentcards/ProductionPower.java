package it.polimi.ingsw.model.cards.developmentcards;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.exceptions.DifferentDimension;
import it.polimi.ingsw.exceptions.TooManyResourcesRequested;

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

    /**
     * Method checkTakenResources verifies that the resources taken from the warehouse by the player are enough to use a production power
     * @param resources is the array of resources chosen by the player to activate the production power
     * @param warehouse is the array of Warehouse objects that shows where the chosen resources come from
     * @param board is the player board
     * @return true if the resources are enough, false otherwise
     * @throws TooManyResourcesRequested is the exception thrown when the resources aren't enough
     */
    public boolean checkTakenResources(ArrayList<ResourceType> resources, ArrayList<Warehouse> warehouse, Board board) throws TooManyResourcesRequested {
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

    /**
     * Method addResource increase the number of the strongbox resources by adding the resource obtained thanks to the production power
     * method called when the production power doesn't have ResourceType.JOLLY in the resource obtained
     * @param board id the player board
     */
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

    /**
     * Method addResource increase the number of the strongbox resources by adding the resource obtained thanks to the production power
     * method called when the production power has ResourceType.ANY in the resource obtained
     * @param board id the player board
     * @param newResources is the array of chosen resources by the player instead of the JOLLY resources
     * @throws DifferentDimension is the exception thrown when the number of chosen resources is different from the number of JOLLY resources
     */
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