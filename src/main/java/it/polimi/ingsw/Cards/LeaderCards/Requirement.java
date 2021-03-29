package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.*;
import java.util.HashMap;

public class Requirement {
    private HashMap<CardColor,Integer> color;
    private Level level;
    private HashMap<ResourceType,Integer> resourcePrice;

    public HashMap<CardColor, Integer> getColor() {
        return color;
    }

    public Level getLevel() {
        return level;
    }

    public HashMap<ResourceType, Integer> getResourcePrice() {
        return resourcePrice;
    }
}
