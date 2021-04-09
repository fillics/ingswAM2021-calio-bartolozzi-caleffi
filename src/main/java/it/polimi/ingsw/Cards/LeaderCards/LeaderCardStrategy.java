package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;

/**
 * This class represents a generic strategy for Leader Cards.
 */
public interface LeaderCardStrategy {

    /**
     * Method ability() is used by ConcreteStrategy classes to activate the ability of the leader card
     */
    void ability();
}
