package it.polimi.ingsw.Cards.LeaderCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;

import java.util.HashMap;

public class ResourcesRequirements extends Requirement{
    private HashMap<ResourceType,Integer> resourcePrice;

    @JsonCreator
    public ResourcesRequirements(@JsonProperty("resourcePrice") HashMap<ResourceType,Integer> resourcePrice) {
        this.resourcePrice = resourcePrice;
    }

    public HashMap<ResourceType, Integer> getResourcePrice() {
        return resourcePrice;
    }

    @Override
    public boolean check(Board board) {
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
        return "Sono resources";
    }
}
