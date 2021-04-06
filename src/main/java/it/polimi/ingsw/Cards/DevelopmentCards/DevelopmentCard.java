package it.polimi.ingsw.Cards.DevelopmentCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.Card;

import java.util.HashMap;

public class DevelopmentCard extends Card {
    private Level level;
    private CardColor color;
    private HashMap<ResourceType,Integer> resourcePrice;
    private ProductionPower productionPower;
    private int victorypoint;

    /**
     * Constructor DevelopmentCard creates a new DevelopmentCard instance.
     */
    public DevelopmentCard(Level level, CardColor color, HashMap<ResourceType, Integer> resourcePrice, ProductionPower productionPower) {
        this.level = level;
        this.color = color;
        this.resourcePrice = resourcePrice;
        this.productionPower = productionPower;
    }

    public Level getLevel() {
        return level;
    }

    public CardColor getColor() {
        return color;
    }

    public HashMap<ResourceType, Integer> getResourcePrice() {
        return resourcePrice;
    }

    public ProductionPower getProductionPower() {
        return productionPower;
    }

    @Override
    public int getVictoryPoint() {
        return victorypoint;
    }
}
