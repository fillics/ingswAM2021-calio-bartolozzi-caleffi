package it.polimi.ingsw.model.cards.leadercards;

import it.polimi.ingsw.model.board.resources.ResourceType;

/**
 * This class represents a generic strategy for Leader Cards.
 */
public interface LeaderCardStrategy {

    /**
     * Method ability() is used by ConcreteStrategy classes to activate the ability of the leader card
     */
    void ability();

    boolean isActive();

    ResourceType getResourceType();
}
