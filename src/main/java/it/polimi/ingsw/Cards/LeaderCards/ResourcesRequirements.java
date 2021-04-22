package it.polimi.ingsw.Cards.LeaderCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.polimi.ingsw.Board.Board;
import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.CardColor;
import it.polimi.ingsw.Cards.DevelopmentCards.Level;

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
        return false;
    }


    @Override
    public String toString() {
        return "Sono resources";
    }
}
