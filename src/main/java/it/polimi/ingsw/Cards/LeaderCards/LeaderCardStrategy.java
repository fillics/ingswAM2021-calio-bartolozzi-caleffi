package it.polimi.ingsw.Cards.LeaderCards;

import it.polimi.ingsw.Board.Resources.ResourceType;
import it.polimi.ingsw.Cards.DevelopmentCards.DevelopmentCard;

/**
 * This class represents a generic strategy for Leader Cards.
 */
public class LeaderCardStrategy {

    /**
     * Method activate() is used by ConcreteStrategy classes to apply the right activation of the leader card
     */
    public boolean activate(){
        return true;
    }

    /**
     * Methods ability() are used by ConcreteStrategy classes to use the ability of the leader card according to the parameter needed
     */
    public boolean ability(ResourceType resourceObtained) {
        return false;
    }

    public boolean ability(int numofMarbles) {
        return false;
    }

    public boolean ability(DevelopmentCard developmentCard) {
        return false;
    }
}
