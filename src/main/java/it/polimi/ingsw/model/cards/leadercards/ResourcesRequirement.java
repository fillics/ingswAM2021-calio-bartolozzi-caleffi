package it.polimi.ingsw.model.cards.leadercards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.constants.Color;
import it.polimi.ingsw.constants.Printable;
import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.resources.ResourceType;

import java.util.HashMap;

/**
 *  This class represents a specific type of requirement where are needed a certain amount of resources in order to activate
 *  a Leader Card.
 */
public class ResourcesRequirement extends Requirement{
    private final HashMap<ResourceType,Integer> resourcePrice;

    /**
     * Constructor ResourcesRequirement creates a new ResourcesRequirement instance.
     * @param resourcePrice is the map that represents the number of resources needed.
     */
    @JsonCreator
    public ResourcesRequirement(@JsonProperty("resourcePrice") HashMap<ResourceType,Integer> resourcePrice) {
        this.resourcePrice = resourcePrice;
    }

    /**
     * get-method
     * @return tha hashmap that contains the number of resources needed.
     */
    public HashMap<ResourceType, Integer> getResourcePrice() {
        return resourcePrice;
    }

    /**
     * Override method that counts if the number of resources contained in the board is enough to fulfill the requirement
     * @param board is the board of the player where the check will be done .
     * @return true if the requirement is fulfilled, false if not.
     */
    @Override
    public boolean checkRequirement(Board board) {
        HashMap<ResourceType,Integer> counter = new HashMap<>();
        counter.put(ResourceType.COIN,board.getTotalCoins());
        counter.put(ResourceType.STONE,board.getTotalStones());
        counter.put(ResourceType.SERVANT,board.getTotalServants());
        counter.put(ResourceType.SHIELD, board.getTotalShields());
        for(ResourceType key : resourcePrice.keySet()){
            if(resourcePrice.get(key) > counter.get(key)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String escape="";

        if(resourcePrice.containsKey(ResourceType.SHIELD))
        escape= resourcePrice.get(ResourceType.SHIELD) + " " + Color.ANSI_BLUE.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourcePrice.containsKey(ResourceType.COIN))
            escape = escape + resourcePrice.get(ResourceType.COIN) + " " + Color.ANSI_YELLOW.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourcePrice.containsKey(ResourceType.SERVANT))
            escape = escape + resourcePrice.get(ResourceType.SERVANT) + " " + Color.ANSI_PURPLE.escape() + Printable.SQUARE.print() + Color.RESET;
        if(resourcePrice.containsKey(ResourceType.STONE))
            escape = escape + resourcePrice.get(ResourceType.STONE) + " " + Color.ANSI_GREY.escape() + Printable.SQUARE.print() + Color.RESET;
        escape = escape + "   ";

        return escape;
    }

}
