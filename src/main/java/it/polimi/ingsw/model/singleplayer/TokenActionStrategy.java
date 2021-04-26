package it.polimi.ingsw.model.singleplayer;

/**
 * Common interface for all token's strategies.
 */
public interface TokenActionStrategy {

    /**
     * Method effect used by ConcreteStrategy classes to apply the right token effect
     */
    void effect();

}
