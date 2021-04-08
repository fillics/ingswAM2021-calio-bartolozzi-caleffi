package it.polimi.ingsw.Cards.DevelopmentCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.Card;

import java.util.HashMap;

/**
 * This class represents the Development Card.
 */
public class DevelopmentCard extends Card {
    private Level level;
    private CardColor color;
    private HashMap<ResourceType,Integer> resourcePrice;
    private ProductionPower productionPower;
    private int victorypoint;

    /**
     * Constructor DevelopmentCard creates a new DevelopmentCard instance.
     */
    public DevelopmentCard(Level level, CardColor color, ProductionPower productionPower, int victorypoint) {
        this.level = level;
        this.color = color;
        resourcePrice = new HashMap<>();
        this.productionPower = productionPower;
        this.victorypoint= victorypoint;
    }

    /**
     * Method getLevel returns the development card's level.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Method getColor returns the development card's color.
     */
    public CardColor getColor() {
        return color;
    }

    /**
     * Method getResourcePrice returns the development card's price.
     */
    public HashMap<ResourceType, Integer> getResourcePrice() {
        return resourcePrice;
    }

    /**
     * Method getProductionPower returns the development card's production power.
     */
    public ProductionPower getProductionPower() {
        return productionPower;
    }

    /**
     * Override Method getVictoryPoints returns the development card's victory points.
     */
    @Override
    public int getVictoryPoint() {
        return victorypoint;
    }
}
