package it.polimi.ingsw.model.cards.leadercards;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.polimi.ingsw.model.board.resources.ResourceType;
import it.polimi.ingsw.model.marbles.*;

/**
 * This class represents a generic strategy for Leader Cards.
 */


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes(
        {
        @JsonSubTypes.Type(value = ConcreteStrategyMarble.class, name = "WHITE_MARBLE"),
        @JsonSubTypes.Type(value = ConcreteStrategyProductionPower.class, name = "PRODUCTION_POWER"),
        @JsonSubTypes.Type(value = ConcreteStrategyDeposit.class, name = "EXTRA_DEPOSIT"),
        @JsonSubTypes.Type(value = ConcreteStrategyDiscount.class, name = "DISCOUNT")
        })
public interface LeaderCardStrategy {
    /**
     * Method ability() is used by ConcreteStrategy classes to activate the ability of the leader card
     */
    void ability();

    /**
     * Method isActive() returns true or false whether the strategy is active or not.
     */
    boolean isActive();

    /**
     * Method getResourceType() returns the resource type of the strategy.
     */
    ResourceType getResourceType();
}
