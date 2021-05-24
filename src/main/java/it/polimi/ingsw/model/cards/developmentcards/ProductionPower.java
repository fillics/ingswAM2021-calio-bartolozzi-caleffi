package it.polimi.ingsw.model.cards.developmentcards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.client.Color;
import it.polimi.ingsw.client.Printable;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.board.storage.Warehouse;
import it.polimi.ingsw.exceptions.DifferentDimension;
import it.polimi.ingsw.exceptions.TooManyResourcesRequested;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.jar.JarOutputStream;

/**
 * This class represents the Production Power of a Development Card and of the Board.
 */
public class ProductionPower {
    private final HashMap<ResourceType,Integer> resourceNeeded;
    private final HashMap<ResourceType,Integer> resourceObtained;

    /**
     * Constructor ProductionPower creates a new ProductionPower instance.
     */
    @JsonCreator
    public ProductionPower(@JsonProperty("resourceNeeded")HashMap<ResourceType, Integer> resourceNeeded,@JsonProperty("resourceObtained") HashMap<ResourceType, Integer> resourceObtained) {
        this.resourceNeeded = resourceNeeded;
        this.resourceObtained = resourceObtained;
        if(!this.resourceNeeded.containsKey(ResourceType.COIN)){
            this.resourceNeeded.put(ResourceType.COIN,0);
        }
        if(!this.resourceNeeded.containsKey(ResourceType.STONE)){
            this.resourceNeeded.put(ResourceType.STONE,0);

        }
        if(!this.resourceNeeded.containsKey(ResourceType.SERVANT)){
            this.resourceNeeded.put(ResourceType.SERVANT,0);

        }
        if(!this.resourceNeeded.containsKey(ResourceType.SHIELD)){
            this.resourceNeeded.put(ResourceType.SHIELD,0);
        }
    }


    /**
     * Method getResourcesNeeded returns the resources needed to activate the production power.
     */
    public HashMap<ResourceType, Integer> getResourceNeeded() {
        return resourceNeeded;
    }

    /**
     * Method getResourceObtained returns the resources obtained from the activation of the production power.
     */
    public HashMap<ResourceType, Integer> getResourceObtained() {
        return resourceObtained;
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

        System.out.println(counter.get(ResourceType.COIN));
        System.out.println(board.getTotalCoins());
        System.out.println(counter.get(ResourceType.STONE));
        System.out.println(board.getTotalStones());
        System.out.println(counter.get(ResourceType.SERVANT));
        System.out.println(board.getTotalServants());
        System.out.println(counter.get(ResourceType.SHIELD));
        System.out.println(board.getTotalShields());
        
        if(counter.get(ResourceType.COIN) <= board.getTotalCoins() && counter.get(ResourceType.STONE) <= board.getTotalStones() &&
                counter.get(ResourceType.SERVANT) <= board.getTotalServants() && counter.get(ResourceType.SHIELD) <= board.getTotalShields()){
            for (ResourceType key : counter.keySet()) {
                counter.replace(key,counter.get(key)- resourceNeeded.get(key));
                if(counter.get(key)>0){
                    jollycounter += counter.get(key);
                }
            }
            if(resourceNeeded.containsKey(ResourceType.JOLLY)){
                jollycounter -= resourceNeeded.get(ResourceType.JOLLY);
            }
            return counter.get(ResourceType.COIN) >= 0 && counter.get(ResourceType.STONE) >= 0 &&
                    counter.get(ResourceType.SERVANT) >= 0 && counter.get(ResourceType.SHIELD) >= 0 && jollycounter == 0;
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
            for(ResourceType key : resourceObtained.keySet()){
                if(key == ResourceType.FAITHMARKER){
                    for (int i = 0; i< resourceObtained.get(key); i++){
                        board.increaseFaithMarker();
                    }
                }
                else if(!key.equals(ResourceType.JOLLY)) {
                    board.getStrongbox().getStrongbox().replace(key,board.getStrongbox().getStrongbox().get(key) + resourceObtained.get(key));
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
        if(newResources.size() != resourceObtained.get(ResourceType.JOLLY)){
            throw new DifferentDimension();
        }
        else{
            for(ResourceType key : resourceObtained.keySet()){
                if(key == ResourceType.FAITHMARKER){
                    for (int i = 0; i< resourceObtained.get(key); i++){
                        board.increaseFaithMarker();
                    }
                }
                else {
                    if(!key.equals(ResourceType.JOLLY)){
                        board.getStrongbox().getStrongbox().replace(key,board.getStrongbox().getStrongbox().get(key) + resourceObtained.get(key));
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

    @Override
    public String toString(){
        String escape= "";
        if(resourceNeeded.get(ResourceType.SHIELD)!=0 )
            escape= resourceNeeded.get(ResourceType.SHIELD) + Color.ANSI_BLUE.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourceNeeded.get(ResourceType.COIN)!=0)
            escape = escape + resourceNeeded.get(ResourceType.COIN) + Color.ANSI_YELLOW.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourceNeeded.get(ResourceType.SERVANT)!=0)
            escape = escape + resourceNeeded.get(ResourceType.SERVANT) + Color.ANSI_PURPLE.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourceNeeded.get(ResourceType.STONE)!=0)
            escape = escape + resourceNeeded.get(ResourceType.STONE)+ Color.ANSI_GREY.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourceNeeded.containsKey(ResourceType.JOLLY) && resourceNeeded.get(ResourceType.JOLLY)!=0)
            escape = escape + resourceNeeded.get(ResourceType.JOLLY)+ Color.ANSI_GREY.escape() + Printable.CIRCLE.print() + Color.RESET;
        escape = escape+ "}";
        if(resourceObtained != null){
            if(resourceObtained.containsKey(ResourceType.SHIELD))
                escape= escape + resourceObtained.get(ResourceType.SHIELD) + Color.ANSI_BLUE.escape() + Printable.SQUARE.print() + Color.RESET;
            if(resourceObtained.containsKey(ResourceType.COIN))
                escape = escape + resourceObtained.get(ResourceType.COIN)  + Color.ANSI_YELLOW.escape() + Printable.SQUARE.print() + Color.RESET;
            if(resourceObtained.containsKey(ResourceType.SERVANT))
                escape = escape + resourceObtained.get(ResourceType.SERVANT) + Color.ANSI_PURPLE.escape() + Printable.SQUARE.print() + Color.RESET;
            if(resourceObtained.containsKey(ResourceType.STONE))
                escape = escape + resourceObtained.get(ResourceType.STONE)+ Color.ANSI_GREY.escape() + Printable.SQUARE.print() + Color.RESET;
            if(resourceObtained.containsKey(ResourceType.FAITHMARKER))
                escape = escape + resourceObtained.get(ResourceType.FAITHMARKER)+ Color.ANSI_RED.escape() + Printable.CROSS.print() + Color.RESET;
            if(resourceObtained.containsKey(ResourceType.JOLLY))
                escape = escape + resourceObtained.get(ResourceType.JOLLY)+ Color.ANSI_GREY.escape() + Printable.CIRCLE.print() + Color.RESET;
        }
        return escape;
    }

    public int numofResources(){
        int num=0;
        if(resourceNeeded.get(ResourceType.SHIELD)!=0)
            num++;
        if(resourceNeeded.get(ResourceType.COIN)!=0)
            num++;
        if(resourceNeeded.get(ResourceType.SERVANT)!=0)
            num++;
        if(resourceNeeded.get(ResourceType.STONE)!=0)
            num++;
        if(resourceNeeded.containsKey(ResourceType.JOLLY) && resourceNeeded.get(ResourceType.JOLLY)!=0)
            num++;
        if(resourceObtained.containsKey(ResourceType.SHIELD))
            num++;
        if(resourceObtained.containsKey(ResourceType.COIN))
            num++;
        if(resourceObtained.containsKey(ResourceType.SERVANT))
            num++;
        if(resourceObtained.containsKey(ResourceType.STONE))
            num++;
        if(resourceObtained.containsKey(ResourceType.FAITHMARKER))
            num++;
        if(resourceObtained.containsKey(ResourceType.JOLLY))
            num++;

        return num;
    }

}
