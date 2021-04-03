package it.polimi.ingsw.SinglePlayer;

import it.polimi.ingsw.Exceptions.NegativeNumberBlackCross;

/**
 * Common interface for all token's strategies.
 */

public interface TokenActionStrategy {

    /**
     * Method effect used by ConcreteStrategy classes to apply the right token effect
     */
    void effect() throws NegativeNumberBlackCross;

}
