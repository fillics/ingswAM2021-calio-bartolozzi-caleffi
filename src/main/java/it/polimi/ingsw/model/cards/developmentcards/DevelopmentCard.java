package it.polimi.ingsw.model.cards.developmentcards;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.cards.Card;

import java.util.HashMap;

/**
 * This class represents the Development Card.
 */
public class DevelopmentCard extends Card {
    private final int id;
    private final Level level;
    private final CardColor color;
    private final HashMap<ResourceType,Integer> resourcePrice;
    private final ProductionPower productionPower;
    private final int victorypoint;

    /**
     * Constructor DevelopmentCard creates a new DevelopmentCard instance.
     */
    public DevelopmentCard(int id,Level level, CardColor color, ProductionPower productionPower, HashMap<ResourceType,Integer> resourcePrice, int victorypoint) {
        this.id = id;
        this.level = level;
        this.color = color;
        this.resourcePrice= resourcePrice;
        this.productionPower = productionPower;
        this.victorypoint= victorypoint;
    }

    public DevelopmentCard() {
        this.id = 0;
        this.level = null;
        this.color = null;
        this.resourcePrice = null;
        this.productionPower = null;
        this.victorypoint = 0;
    }

    public int getId() {
        return id;
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
    @JsonIgnore
    @Override
    public int getVictoryPoint() {
        return victorypoint;
    }
}
