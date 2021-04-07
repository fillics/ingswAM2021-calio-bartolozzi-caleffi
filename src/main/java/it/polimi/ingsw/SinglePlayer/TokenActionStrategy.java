package it.polimi.ingsw.SinglePlayer;

/**
 * Common interface for all token's strategies.
 */
public interface TokenActionStrategy {

    /**
     * Method effect used by ConcreteStrategy classes to apply the right token effect
     */
    void effect();

}
